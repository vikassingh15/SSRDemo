package com.demo.ssr.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

import javax.script.ScriptEngine;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Configures how Spring will render views to the client.
 */
@Configuration
public class ViewConfig {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private ObjectMapper mapper;

	/**
	 * Configures where to find the views that we can render. Since we
	 * actually do all the rendering in 'render.js', we only have a single
	 * placeholder file.
	 */
    @Bean
    public ViewResolver reactViewResolver() {
        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
        viewResolver.setPrefix("templates/");
        viewResolver.setSuffix(".txt");
        return viewResolver;
    }

	/**
	 * Configures the {@link ScriptEngine} that will render our views.
	 */
    @Bean
    public ScriptTemplateConfigurer reactConfigurer() throws IOException {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");

		/* Initialise the ScriptEngine with these scripts. */
        configurer.setScripts(getScripts());

		/* The ScriptEngine will call this function to perform the render */
        configurer.setRenderFunction("render");

		/* We cannot share a ScriptEngine between threads when rendering React applications */
        configurer.setSharedEngine(false);

        return configurer;
	}

	/**
	 * These are the scripts needed to render our React application.
	 * <ul>
	 *   <li><code>renderer.js</code> - code that renders the response, along with polyfills for some standard functions from a browser / NodeJS environment, and the contents of asset-manifest.json.</li>
	 *   <li><code>main.[hash].js</code> - all our application code, bundled up by Webpack, with a hashcode in the name</li>
	 * </ul>
	 */
	private String[] getScripts() throws IOException {
		return new String[] {
			"js/renderer.js",
			getBundleName()
		};
	}

	private String getBundleName() throws IOException {
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
		Map<String, String> manifest = mapper.readValue(new ClassPathResource("public/asset-manifest.json").getInputStream(), typeRef);
		return "public/" + manifest.get("main.js");
	}
}
