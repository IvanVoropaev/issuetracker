package com.voropaev.issuetracker.web;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

/**
 * @author Ivan Voropaev
 * <p>�������� ����������, �������������� ������ ����������</p>
 */
@Controller
public class IssueController {
	
	@Autowired
	protected IssueTrackerService service;
	
	/**
	 * <p>�����, �������������� ������ ������� �������� - ����������� ��������� ���� ������, ��������� � ���� ������,
	 *    � �������� �� � ������ ��� ����������� � ������������� (�� ������� �������� ����������)</p>
	 *
	 *@param request ������������ ��� ��������� ��������� �� ������ �����������
	 */
	@RequestMapping(value = "/")
	public ModelAndView showListOfIssues(HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		List<Issue> issueList= service.getAllIssue();
		
		model.addObject("message", request.getSession().getAttribute("message"));
		model.addObject("issues", issueList);
		model.setViewName("index");
		
		return model;
	}
	
	/**
	 * <p>�����, ���������� ��� ������� ������� �� �������� � ������ ��� ���������� ����� ������ � ������.
	 *    ���������, ���� �� � ������ ������ ���������������� ������������ � � ������ �������������, ���������� 
	 *    ������������ �� �������� � ������. �������������������� ������������ ����� ������� ���� ����� ������ ���� ������ 
	 *    � ������ �������� �����.  �� ���������� ����� �������� ������ ��� ��������������� �������������.</p>
	 *
	 *@param model - ������
	 *@param principal - ������, ���������� ���������� �� ���������������� ������������
	 */
	@RequestMapping("/addissue")
	public ModelAndView showAddIssueForm(ModelAndView model, Principal principal) {
		
		if(principal==null) {
			String message = "You must loggin in before you can add issue";
			model.addObject("message", message);
			model.setViewName("errors/error");
			return model;
		}

		model.addObject("user", service.getUserByEmail(principal.getName()));	
		model.addObject("issue", new Issue());
		model.setViewName("createissue");
		
		return model;
	}
	
	/**
	 * <p>����� ������������ ������ POST, ������������ ������ ��� ���������� ����� ������ � ����. 
	 *    � ������, ���� ���� �������� ������ ���������, ����� ����� �������������� ������������ �� �������� � ������ � ��������� ������, 
 	 *    ���������� ��� ����������. � ������ ��������� �������� ��������� ������������ ��������������� �� ��������� ��������</p>
	 *
	 *@param issue - ������, ������������ �����
	 *@param bindingResult ������, ���������� ���������� �� ������� ��������� (������� �������� ������ �����)
	 *@param principal - ������, ���������� ���������� �� ���������������� ������������
	 */
	@RequestMapping(value = "/addissue", method = RequestMethod.POST)
	public ModelAndView addIssue(@Valid Issue issue, BindingResult bindingResult, Principal principal) {
		
		ModelAndView model = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			model.setViewName("createissue");
			return model;
		}
		
		User user = service.getUserByEmail(principal.getName());
		issue.setUser(user);
		//�������� ���� �������� ��������� �� ������
		Date date = new Date();
		issue.setIssueDate(new Timestamp(date.getTime()));
		
		issue.setIssueStatus("Created");
		service.addIssue(issue);
		model.setViewName("redirect:");
		
		return model;
	}
	
	/**
	 * <p>����� �������� �������� ��� ��������� ��������� �� ������ � ������������. � ������������� ���������� �������� �� ������ (issue)
	 *    ������������, ���������� �������� �� ������ (user) � ��� ����������� � ��������� � ���������.
	 *    � �������� ��������� ����� �������� id ��������� �� ������ (issue Id) </p>
	 *
	 *@param issueId - id ��������� �� ������
	 *@param request - ������, ���������� ���������� � �������, ����������� ��� ��������� ������� Issue ����� ���������� ����������� ��� ��������� ������� ��������� �� ������
	 *@param principal - ������, ���������� ���������� �� ���������������� ������������
	 */
	@RequestMapping("/issue/{issueId}")
	public ModelAndView viewAndCommentIssue(@PathVariable Integer issueId, HttpServletRequest request, Principal principal) {
		ModelAndView model = new ModelAndView();
		Issue issue = service.getIssueById(issueId);
		User user = issue.getUser();
		List<Comment> listComment = service.getCommentsByIssueId(issueId);
		request.getSession().setAttribute("issue", issue);
		if(!(principal==null)) {
			User principalUser = service.getUserByEmail(principal.getName());
			model.addObject("principalUser", principalUser);
		}
		model.addObject("comment", new Comment());
		model.addObject("comments", listComment);
		model.addObject("issue", issue);
		model.addObject("user", user);
		model.setViewName("issuetemplate");
		return model;
	}
	
	/**
	 * <p>����� ������������ ������ POST, ������������ ������ ��� ���������� �����������. 
	 *    � ������, ���� ���� �������� ������ ���������, ����� ����� �������������� ������������ �� �������� � ������ � ��������� ������, 
 	 *    ���������� ��� ����������. � ������ ��������� �������� ��������� ������������ ���������������� �� ����������� �������� � ����������.</p>
	 *
	 *@param comment - ������, ������������ �����
	 *@param bindingResult ������, ���������� ���������� �� ������� ��������� (������� �������� ������ �����)
	 *@param principal - ������, ���������� ���������� �� ���������������� ������������
	 */
	@RequestMapping(value = "/addcomment", method = RequestMethod.POST)
	public ModelAndView addComment(@Valid Comment comment, /*Issue issue,*/ BindingResult bindingResult, Principal principal, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			model.setViewName("createissue");
			return model;
		}
		
		User user = service.getUserByEmail(principal.getName());
		comment.setUser(user);
		
		Date date = new Date();
		comment.setCommentDate(new Timestamp(date.getTime()));	
		
		Issue issue = (Issue) request.getSession().getAttribute("issue");
		issue.setIssueStatus(comment.getCommentStatus());
		service.updateIssueStatus(issue);
		comment.setIssue(issue);
		
		service.addComment(comment);
		
		model.addObject("user", user);
		model.setViewName("redirect:issue/" + comment.getIssue().getId());
		
		return model;
	}
	
}
