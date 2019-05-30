package com.demo.ssr.home;

import com.demo.ssr.comments.CommentRepository;
import com.demo.ssr.utils.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Renders the home page. It loads all the comments in the repository
 * and passes them to the render context in the same shape that Redux
 * uses.
 */
@Controller
public class HomeController {

	@Autowired
    private CommentRepository repository;

    @RequestMapping(value = "/", method = GET)
    public String index(Model model, HttpServletRequest request) {
		State.populateModel(model, request);
        model.addAttribute("comments", getCommentsState());
        return "index";
    }

	private Map<String, Object> getCommentsState() {
		Map<String,Object> state = new HashMap<>();
		state.put("status", "loaded");
		state.put("data", repository.findAll());
		return state;
	}
}
