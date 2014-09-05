package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voropaev.issuetracker.config.TestConfig;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class} )
public class IssueMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	IssueMapper issueMapper;
	
	private Integer id = 1;
	private GregorianCalendar date = new GregorianCalendar(2014, 8, 5, 15, 14, 30);
	private String issueName = "issuename";
	private String issueStatus = "issuestatus";
	private String issueDescription = "issuedescription";
	
	private Issue issue;
	private User user;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		issue = new Issue();
		user = new User();
		user.setId(1);
		issue.setUser(user);
		issue.setId(id);
		issue.setIssueName(issueName);
		issue.setIssueDate(date.getTime());
		issue.setIssueStatus(issueStatus);
		issue.setIssueDescription(issueDescription);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllIssue() {
		List<Issue> list = issueMapper.getAllIssue();
		assertTrue(list.size() == 1);
	}
	
	@Test
	public void testAddIssue() {		
		int before = countRowsInTable("issue");
		issueMapper.addIssue(issue);
		int after = countRowsInTable("issue");
		assertTrue((before + 1) == after);
	}
	
	@Test
	public void GetIssueById() {
		Issue issueById = issueMapper.getIssueById(issue.getId());
		assertEquals(issue, issueById);
	}

}
