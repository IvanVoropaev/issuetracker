package com.voropaev.issuetracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping(value = "/page-not-found")
	public ModelAndView pageNotFound() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Page not found");
		model.setViewName("errors/error");
		return model;
	}
	
	@RequestMapping(value = "/uncaught-error")
	public ModelAndView uncaughtError() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Unknown error");
		model.setViewName("errors/error");
		return model;
	}
}
