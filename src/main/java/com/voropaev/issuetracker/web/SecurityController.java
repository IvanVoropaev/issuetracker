package com.voropaev.issuetracker.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.service.IssueTrackerService;

@Controller
public class SecurityController {
	
	@Autowired
	protected IssueTrackerService service;
	
	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;
	
	@RequestMapping("/login")
	public ModelAndView showLoginForm() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping("/registration")
	public ModelAndView showRegForm(ModelAndView model) {
		model.addObject(new User());
		model.setViewName("regtemplate");
		return model;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("regtemplate");
			return model;
		}
		
		if(!(service.getUserByEmail(user.getUserEmail())==null)) {
			bindingResult.addError(new FieldError(bindingResult.getObjectName(), "userEmailError", "Specified email is already taken."));
			System.out.println("email error");
			model.setViewName("regtemplate");
			return model;
		}
		
		service.insertUser(user);
		model.setViewName("index");
		model.addObject("bindingResult", bindingResult);
		model.addObject("user", user);
		
		// After successfully Creating user
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
		user.getUserEmail(), user.getPassword());
				 
		// generate session if one doesn't exist
		request.getSession();
				 
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);
				 
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		
		return model;
	}
}
