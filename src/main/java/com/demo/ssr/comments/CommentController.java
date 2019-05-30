package com.demo.ssr.comments;

import com.demo.ssr.utils.State;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Handles requests for the "add a comment" page. This is handled
 * by our UI stack without any additional context.
 */

@Controller
public class CommentController {

    @RequestMapping(value = "/add", method = GET)
    public String index(Model model, HttpServletRequest request) {
		State.populateModel(model, request);
        return "index";
    }
}
