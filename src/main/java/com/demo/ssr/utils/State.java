package com.demo.ssr.utils;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public final class State {

	/** Populates standard parts of the shared client/server model into the Spring {@link Model}.
	 * Values prefixed with "__" will be made available to the JavaScript
	 * render function only. All other values will be passed in the client's state object.
	 */
	public static void populateModel(Model model, HttpServletRequest request) {
		model.addAttribute("__requestPath", getRequestPath(request));
	}

	/**
	 * Returns the request string, including the query fragment, so that it can be made available
	 * during server-side react-router rendering.
	 */
	private static String getRequestPath(HttpServletRequest request) {
		String queryString = request.getQueryString();
		return request.getRequestURI() + (queryString == null ? "" : "?" + queryString);
	}

}
