package com.voropaev.issuetracker.service;

import java.util.List;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;


public interface IssueTrackerService {
	public void insertUser(User user);
	public List<User> getAllUsers();
	public User getUserById(Integer userId);
	public User getUserByEmail(String email);
	public List<Issue> getAllIssue();
	public Issue getIssueById(Integer id);
	public void addIssue(Issue issue);
	public void addComment(Comment comment);
	public List<Comment> getCommentsByIssueId(Integer issueId);
}
