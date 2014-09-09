package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voropaev.issuetracker.config.TestConfig;
import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class} )
public class CommentMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	CommentMapper commentMapper;
	
	private Integer id = 1;
	private GregorianCalendar date = new GregorianCalendar(2014, 8, 5, 15, 14, 30);
	private String status = "commentstatus";
	private String text = "commenttext";
	
	private User user;
	private Issue issue;
	private Comment comment;

	@Before
	public void setUp() throws Exception {
		user = new User();
		issue = new Issue();
		comment = new Comment();
		user.setId(1);
		issue.setId(1);
		comment.setId(id);
		comment.setCommentDate(date.getTime());
		comment.setCommentStatus(status);
		comment.setCommentText(text);
		comment.setIssue(issue);
		comment.setUser(user);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCommentsByIssueId() {
		List<Comment> commentByIssueId = commentMapper.getCommentsByIssueId(issue.getId());
		assertEquals(comment, commentByIssueId.get(0));
	}
	
	@Test
	public void testAddComment() {
		int before = countRowsInTable("comments");
		commentMapper.addComment(comment);
		int after = countRowsInTable("comments");
		assertTrue((before + 1) == after);
	}

}
