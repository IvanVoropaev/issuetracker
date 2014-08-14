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
 * <p>Основной контроллер, обеспечивающий работу приложения</p>
 */
@Controller
public class IssueController {
	
	@Autowired
	protected IssueTrackerService service;
	
	/**
	 * <p>Метод, обеспечивающий работу главной страницы - запрашивает коллекцию всех ошибок, внесенных в базу данных,
	 *    и передает ее в модели для отображения в представлении (на главной странице приложения)</p>
	 *
	 *@param request используется для получения сообщения об ошибке авторизации
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
	 * <p>Метод, вызываемый при попытке перейти на страницу с формой для добавления новой ошибки в трекер.
	 *    Проверяет, есть ли в данный момент авторизированный пользователь и в случае подтверждения, направляет 
	 *    пользователя на страницу с формой. Незарегистрированный пользователь может вызвать этот метод только явно набрав 
	 *    в строке браузера адрес.  Из приложения метод доступен только для авторизированых пользователей.</p>
	 *
	 *@param model - модель
	 *@param principal - объект, содержащий информацию об авторизированном пользователе
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
	 * <p>Метод обрабатывает запрос POST, направляемый формой для добавления новой ошибки в базу. 
	 *    В случае, если были выявлены ошибки валидации, метод снова перенаправляет пользователя на страницу с формой с указанием ошибок, 
 	 *    допущенных при заполнении. В случае успешного создания сообщения пользователь перенапрвляется на начальную страницу</p>
	 *
	 *@param issue - объект, передаваемый фомой
	 *@param bindingResult объект, содержащий информацию об ошибках валидации (попытка отправки пустой формы)
	 *@param principal - объект, содержащий информацию об авторизированном пользователе
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
		//внесение даты создания сообщения об ошибке
		Date date = new Date();
		issue.setIssueDate(new Timestamp(date.getTime()));
		
		issue.setIssueStatus("Created");
		service.addIssue(issue);
		model.setViewName("redirect:");
		
		return model;
	}
	
	/**
	 * <p>Метод вызывает страницу для просмотра сообщения об ошибке и комментариев. В представление передается собщение об ошибке (issue)
	 *    Пользователь, добавивший сообщени об ошибке (user) и все комментарии к сообщению в коллекции.
	 *    В качестве параметра метод получает id сообщения об ошибке (issue Id) </p>
	 *
	 *@param issueId - id сообщения об ошибке
	 *@param request - объект, содержащий информацию о запросе, исользуется для получения объекта Issue после добавления комментария для изменения статуса сообщения об ошибке
	 *@param principal - объект, содержащий информацию об авторизированном пользователе
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
	 * <p>Метод обрабатывает запрос POST, направляемый формой для добавления комментария. 
	 *    В случае, если были выявлены ошибки валидации, метод снова перенаправляет пользователя на страницу с формой с указанием ошибок, 
 	 *    допущенных при заполнении. В случае успешного создания сообщения пользователь перенапарвляется на обновленную страницу с сообщением.</p>
	 *
	 *@param comment - объект, передаваемый фомой
	 *@param bindingResult объект, содержащий информацию об ошибках валидации (попытка отправки пустой формы)
	 *@param principal - объект, содержащий информацию об авторизированном пользователе
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
