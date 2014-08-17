package com.voropaev.issuetracker.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ivan Voropaev
 * 
 * <p>Объект - сущность для таблицы users.</p>.
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = -3663290383064782272L;
	
	private Integer id;
	@NotEmpty
	@Size(min=3, max=30, message="Username must be between 3 and 20 characters long.")
	private String userName;
	@NotEmpty(message="Email, please.") 
	@Email
	private String userEmail;
	@NotEmpty
	@Size(min=4, max=20, message="The password must be at least 4 characters long.")
	private String password;
	private List<Issue> issues;
	private List<Comment> comments;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "User: [Id: " + id + ". Name: " + userName + ". Email: " + userEmail + ".]";
	}
	
}
