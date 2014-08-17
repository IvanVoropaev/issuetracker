package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/WEB-INF/spring/root-context.xml"})
public class IssueMapperTest {
	
	private static final String userName = "userName";
	private static final String userEmail = "email@email.com";
	private static final String userPassword = "password";
	private User user;
	
	private static final Date issueDate = getTime();
	private static final String issueName = "issueName";
	private static final String issueStatus = "issueStatus";
	private static final String issueDescription = "issueDescription";
	private Issue issue;
	
	
	@Autowired
	private IssueMapper issueMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	List<Issue> issueList;
	List<Issue> issueListAfterInsert;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(userPassword);
		userMapper.insertUser(user);
		issue = new Issue();
		issue.setIssueName(issueName);
		issue.setIssueStatus(issueStatus);
		issue.setIssueDate(issueDate);
		issue.setUser(userMapper.getUserByEmail(userEmail));
		issueList = issueMapper.getAllIssue();
		issueMapper.addIssue(issue);	
		issueListAfterInsert = issueMapper.getAllIssue();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetAllIssue() {
		assertNotNull(issueMapper.getAllIssue());
	}

	@Test
	public void testAddIssue() {
		int before = issueList.size();
		int after = issueListAfterInsert.size();
		assertEquals(before + 1, after);
	}

	@Test
	public void testGetIssueById() {
		Issue issueById = issueListAfterInsert.get(0);
		assertNotNull(issueMapper.getIssueById(issueById.getId()));
	}

	@Test
	public void testUpdateIssueStatus() {
		fail("Not yet implemented");
	}
	
	private static Date getTime() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

}
