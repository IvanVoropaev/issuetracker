package com.voropaev.issuetracker.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author Ivan Voropaev
 * <p>Контроллер обеспечивает работу модуля Spring Security</p>
 * <p>Для регистрации в системе требуется указать Имя, Email и пароль, авторизация по email.
 *    Имя пользователя используется при создании отчетов и комментировании.</p>
 */

@Controller
public class SecurityController {
	
	@Autowired
	protected IssueTrackerService service;
	
	@Autowired
	//@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;
	
	/**
 	 * <p>Метод вызывает страницу с формой регистрации пользователя</p>
	 */
	@RequestMapping("/registration")
	public ModelAndView showRegForm() {
		ModelAndView model = new ModelAndView();
		model.addObject(new User());
		model.setViewName("regtemplate");
		return model;
	}
	
	/**
 	 * <p>Метод обрабатывает запрос POST, направляемый формой регистриции 
 	 *    нового пользователя. В случае, если были выявлены ошибки валидации, метод 
 	 *    снова перенаправляет пользователя на страницу с формой регистрации с указанием ошибок, 
 	 *    допущенных при заполнении формы. Так же метод проверяет наличие в базе данных объекта,
 	 *    с указанным в форме email-ом. В случае совпадения, регистрация нового пользователя отклоняется.
 	 *    В случае успешной регистрации, вновь созданный пользователь 
 	 *    автоматически авторизируется в системе и перенапраляется на начальную страницу</p>
 	 *    
 	 * @param user объект, передаваемый фомой регистрации пользователя
 	 * @param bindingResult объект, содержащий информацию об ошибках валидации нового пользователя
 	 * @param request объект, содержащий информацию о запросе, в методе используется для генерации новой сессии при авторизации вновь созданного пользователя
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("regtemplate");
			return model;
		}
		
		//проверка на наличие в базе данных пользователя с указанными в форме email-ом
		if(!(service.getUserByEmail(user.getUserEmail())==null)) {
			bindingResult.addError(new FieldError(bindingResult.getObjectName(), "userEmailError", "Specified email is already taken."));
			model.setViewName("regtemplate");
			return model;
		}
		
		service.insertUser(user);
		model.setViewName("redirect:");
		model.addObject("bindingResult", bindingResult);
		model.addObject("user", user);
		
		// После успешного создания пользователя
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (
		user.getUserEmail(), user.getPassword());
				 
		// генерация новой сессии, в случае если еще не была создана
		request.getSession();
				 
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authenticatedUser = authenticationManager.authenticate(token);
				 
		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
		
		return model;
	}
	
	/**
 	 * <p>Метод вызывается в случае возникновения ошибки при авторизации пользователя в системе.
 	 *    Пользователь перенаправляется на начальную страницу с сообщением об ошибке авторизации.</p>
 	 * 
 	 * @param request объект, содержащий информацию о запросе, используется для внесения в сессию сообщение об ошибке авторизации
	 */
	@RequestMapping("/login-error")
	public ModelAndView loginError(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String message = "Incorrect password/username";
		request.getSession().setAttribute("message", message);
		model.setViewName("redirect:");
		return model;
	}
}
