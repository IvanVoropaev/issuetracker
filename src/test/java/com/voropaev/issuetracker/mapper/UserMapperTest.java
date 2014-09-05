package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voropaev.issuetracker.config.TestConfig;
import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class} )
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	UserMapper userMapper;
	
	private Integer id = 1;
	private String userName = "username";
	private String userEmail = "mail@mail.com";
	private String password = "password";
	
	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setPassword(password);	
	}

	@Test
	public void testInsertUser() {
		/*Date date = new Date();
		System.out.println(new Timestamp(date.getTime()));*/
		int before = countRowsInTable("users");
		userMapper.insertUser(user);
		int after = countRowsInTable("users");
		assertTrue((before + 1) == after);
	}
	
	@Test
	public void testGetUserById() {
		User userById = userMapper.getUserById(user.getId());
		assertEquals(user, userById);
	}
	
	@Test
	public void testGetUserByEmail() {
		User userByEmail = userMapper.getUserByEmail(user.getUserEmail());
		assertEquals(user, userByEmail);
	}

}
