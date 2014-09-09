package com.voropaev.issuetracker.service;

//import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.mapper.CommentMapper;
import com.voropaev.issuetracker.mapper.IssueMapper;
import com.voropaev.issuetracker.mapper.UserMapper;

@RunWith(MockitoJUnitRunner.class)
public class IssueTrackerServiceImplTest {
		
	@Mock
	CommentMapper commentMapper;
	
	@Mock
	IssueMapper issueMapper;
	
	@Mock
	UserMapper userMapper;
	
	@InjectMocks
	IssueTrackerServiceImpl service;

	@Test(expected = RuntimeException.class)
	public void testInserUser() {
		User user = new User();
		doThrow(new RuntimeException()).when(userMapper).insertUser(user);
		service.insertUser(user);
	}
	
	@Test
	public void testGetUserById() {
		Integer id = 1;
		User user = new User();
		when(userMapper.getUserById(id)).thenReturn(user);
		assertEquals(user, service.getUserById(id));
	}
	
	@Test
	public void testGetUserByEmail() {
		String email = "email";
		User user = new User();
		when(userMapper.getUserByEmail(email)).thenReturn(user);
		assertEquals(user, service.getUserByEmail(email));
	}
	
	@Test
	public void testGetAllIssue() {
		List<Issue> issueList = new ArrayList<Issue>();
		when(issueMapper.getAllIssue()).thenReturn(issueList);
		assertEquals(issueList, service.getAllIssue());
	}
	
	@Test(expected = RuntimeException.class)
	public void testAddIssue() {
		Issue issue = new Issue();
		doThrow(new RuntimeException()).when(issueMapper).addIssue(issue);
		service.addIssue(issue);
	}
	
	@Test(expected = RuntimeException.class)
	public void testAddComment() {
		Comment comment = new Comment();
		doThrow(new RuntimeException()).when(commentMapper).addComment(comment);
		service.addComment(comment);
	}
	
	@Test
	public void testGetIssueById() {
		Integer id = 1;
		Issue issue = new Issue();
		when(issueMapper.getIssueById(id)).thenReturn(issue);
		assertEquals(issue, service.getIssueById(id));
	}
	
	@Test
	public void testGetCommentsByIssueId() {
		Integer issueId = 1;
		List<Comment> commentList = new ArrayList<Comment>();
		when(commentMapper.getCommentsByIssueId(issueId)).thenReturn(commentList);
		assertEquals(commentList, service.getCommentsByIssueId(issueId));
	}
	
	@Test(expected = RuntimeException.class)
	public void testUpdateIssueStatus() {
		Issue issue = new Issue();
		doThrow(new RuntimeException()).when(issueMapper).updateIssueStatus(issue);
		service.updateIssueStatus(issue);
	}

}
