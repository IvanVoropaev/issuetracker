package com.voropaev.issuetracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Controller
public class ErrorController {

	//@RequestMapping(value = "/page-not-found")
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	public ModelAndView pageNotFound() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Page not found");
		model.setViewName("errors/error");
		return model;
	}
	
	//@RequestMapping(value = "/uncaught-error")
	@ExceptionHandler(java.lang.Exception.class)
	public ModelAndView uncaughtError() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Unknown error");
		model.setViewName("errors/error");
		return model;
	}
}
