package com.voropaev.issuetracker.web;

//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.voropaev.issuetracker.config.TestControllerConfig;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.service.IssueTrackerService;
import com.voropaev.issuetracker.utils.InitializeEntityForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestControllerConfig.class})
@ActiveProfiles("test")
public class IssueControllerTest {
	
	@Mock
	private IssueTrackerService service;
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpSession session;
	
	@Mock
	private Principal principal;
	
	@InjectMocks
	private IssueController issueController;
	
	private MockMvc mockMvc;

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}*/

	@Before
	public void setUp() throws Exception {
		// Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
	}

	/*@After
	public void tearDown() throws Exception {
	}*/

	@Test
	public void testShowListOfIssues() throws Exception {
		List<Issue> listOfIssue = new ArrayList<Issue>();
		listOfIssue.add((Issue) InitializeEntityForTest.getEntityForTest(Issue.class));
		when(service.getAllIssue()).thenReturn(listOfIssue);
		when(request.getSession()).thenReturn(session);		
		request.getSession().setAttribute("message", "messagetext");

		this.mockMvc.perform(get("/"))
			.andExpect(model().size(2))
			.andExpect(model().attribute("message", request.getSession().getAttribute("message")))
			.andExpect(model().attribute("issues", listOfIssue))
			.andExpect(forwardedUrl("index"));		
	}

	@Test
	public void testShowAddIssueFormIfPrincipalNotNull() throws Exception {
		when(service.getUserByEmail("email")).thenReturn((User) InitializeEntityForTest.getEntityForTest(User.class));
		when(principal.getName()).thenReturn("email");
		System.out.println(service.getUserByEmail(principal.getName()));
		
		this.mockMvc.perform(get("/addissue"))
			.andExpect(model().size(2))
			.andExpect(model().attribute("user", service.getUserByEmail(principal.getName())))
			.andExpect(model().attribute("issue", (Issue) InitializeEntityForTest.getEntityForTest(Issue.class)))
			.andExpect(forwardedUrl("createissue"));
	}

	/*@Test
	public void testAddIssue() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAndCommentIssue() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddComment() {
		fail("Not yet implemented");
	}*/

}
