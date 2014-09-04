package com.voropaev.issuetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.mapper.CommentMapper;
import com.voropaev.issuetracker.mapper.IssueMapper;
import com.voropaev.issuetracker.mapper.UserMapper;

@Service
@Transactional
public class IssueTrackerServiceImpl implements IssueTrackerService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IssueMapper issueMapper;
	@Autowired
	private CommentMapper commentMapper;

	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	public List<Issue> getAllIssue() {
		return issueMapper.getAllIssue();
	}

	public void addIssue(Issue issue) {
		issueMapper.addIssue(issue);
	}

	public void addComment(Comment comment) {
		commentMapper.addComment(comment);
	}

	public Issue getIssueById(Integer id) {
		return issueMapper.getIssueById(id);
	}

	public List<Comment> getCommentsByIssueId(Integer issueId) {
		return commentMapper.getCommentsByIssueId(issueId);
	}

	public void updateIssueStatus(Issue issue) {
		issueMapper.updateIssueStatus(issue);
	}

}
