package com.voropaev.issuetracker.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>Контроллер для обработки возникающих ошибок.</p>
 *
 */
@Controller
public class ErrorController {
	
	/**
	 * 
	 *<p>Обработка ошибки 404.</p>
	 *
	 */
	@RequestMapping(value = "/page-not-found")
	public ModelAndView pageNotFound() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Page not found");
		model.setViewName("errors/error");
		return model;
	}
	
	/**
	 * 
	 *<p>Обработка исключений.</p>
	 *
	 */
	@RequestMapping(value = "/uncaught-error")
	public ModelAndView uncaughtError() {
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Unknown error");
		model.setViewName("errors/error");
		return model;
	}
}
