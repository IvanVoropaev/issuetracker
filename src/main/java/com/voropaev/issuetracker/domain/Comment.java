package com.voropaev.issuetracker.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Ivan Voropaev
 * 
 * <p>Объект - сущность для таблицы comments.</p>.
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 8007841197392187746L;
	
	private Integer id;
	private Date commentDate;
	@NotEmpty
	private String commentStatus;
	@NotEmpty
	private String commentText;
	private User user;
	private Issue issue;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Issue getIssue() {
		return issue;
	}
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	@Override
	public String toString() {
		return "Comment: + [Id: " + id + ". Date: " + commentDate + ". Text: " + commentText + ".]";
	}
}
