package com.voropaev.issuetracker.utils;

import java.util.GregorianCalendar;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

public class InitializeEntityForTest {
	
	private static User user = new User();
	private static Issue issue = new Issue();
	private static Comment comment = new Comment();
	
	public static Object getEntityForTest(Class<?> c) {
		
		user.setId(1);
		user.setUserName("username");
		user.setUserEmail("mail@mail.com");
		user.setPassword("password");
		
		issue.setId(1);
		issue.setIssueDate(new GregorianCalendar(2014, 8, 5, 15, 14, 30).getTime());
		issue.setIssueDescription("issuedescription");
		issue.setIssueName("issuename");
		issue.setIssueStatus("issuestatus");
		issue.setUser(user);
		
		comment.setId(1);
		comment.setCommentDate(new GregorianCalendar(2014, 8, 5, 15, 14, 30).getTime());
		comment.setCommentStatus("commentstatus");
		comment.setCommentText("commenttext");
		comment.setIssue(issue);
		comment.setUser(user);
		
		if(c.equals(User.class)) {
			return user;
		}
		else if(c.equals(Issue.class)) {
			return issue;
		}
		else if(c.equals(Comment.class)) {
			return comment;
		}
		else
			return null;
	}
}
