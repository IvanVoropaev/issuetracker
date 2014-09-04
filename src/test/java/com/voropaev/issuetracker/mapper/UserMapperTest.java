package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.voropaev.issuetracker.config.TestConfig;
import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class} )
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserMapperTest {
	
	@Autowired
	UserMapper userMapper;
	
	private String userName = "username";
	private String userEmail = "email@email.com";
	private String password = "password";

	/*@Before
	public void setUp() throws Exception {
		User user = new User();
	}*/

	/*@After
	public void tearDown() throws Exception {
	}*/

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(password);
		userMapper.insertUser(user);
		User userByEmail = userMapper.getUserByEmail(userEmail);
		assertEquals(user, userByEmail);
		fail("Not yet implemented");
	}
	
	@Ignore
	@Test
	public void testGetUserById() {
		fail("Not yet implemented");
	}
	
	@Ignore
	@Test
	public void testGetUserByEmail() {
		fail("Not yet implemented");
	}

}
