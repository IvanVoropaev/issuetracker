package com.voropaev.issuetracker.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.service.IssueTrackerService;

@Controller
public class IssueController {
	
	/*@Autowired
	protected IssueMapper issueMapper;
	
	@Autowired
	protected UserMapper userMapper;*/
	
	@Autowired
	protected IssueTrackerService service;
	
	@RequestMapping("/")
	public ModelAndView showListOfIssues() {
		
		ModelAndView model = new ModelAndView();
		List<Issue> issueList= service.getAllIssue();
		model.addObject("issues", issueList);
		List<User> userList= service.getAllUsers();
		model.addObject("users", userList);
		model.setViewName("index");
		
		return model;
	}
	
	@RequestMapping("/addissue")
	public ModelAndView showAddIssueForm(ModelAndView model, Principal principal) {

		model.addObject("user", service.getUserByEmail(principal.getName()));	
		model.addObject("issue", new Issue());
		model.setViewName("createissue");
		
		return model;
	}
	
	@RequestMapping(value = "/addissue", method = RequestMethod.POST)
	public ModelAndView addIssue(@Valid Issue issue, BindingResult bindingResult, Principal principal) {

		ModelAndView model = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			model.setViewName("createissue");
			return model;
		}
		
		User user = service.getUserByEmail(principal.getName());
		issue.setUser(user);
		Date date = new Date();
		issue.setIssueDate(new Timestamp(date.getTime()));
		issue.setIssueStatus("Created");
		service.addIssue(issue);
		model.setViewName("redirect:");
		
		return model;
	}
	
	@RequestMapping("/issue/{issueId}")
	public ModelAndView viewAndCommentIssue(@PathVariable Integer issueId) {
		ModelAndView model = new ModelAndView();
		Issue issue = service.getIssueById(issueId);
		User user = issue.getUser();
		List<Comment> listComment = service.getCommentsByIssueId(issueId);
		model.addObject("comments", listComment);
		model.addObject("issue", issue);
		model.addObject("user", user);
		model.setViewName("issuetemplate");
		return model;
	}
	
}
