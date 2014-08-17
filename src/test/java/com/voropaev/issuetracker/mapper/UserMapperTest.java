package com.voropaev.issuetracker.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.voropaev.issuetracker.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/WEB-INF/spring/root-context.xml"})
public class UserMapperTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String name = "userName";
	private static final String email = "email@email.com";
	private static final String password = "password";
	private User user;
	
	@Autowired
	UserMapper userMapper;
	
	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setUserName(name);
		user.setUserEmail(email);
		user.setPassword(password);
		userMapper.insertUser(user);
		System.out.println("Before: " + user);
	}

	@After
	public void tearDown() {
		User delUser = userMapper.getUserByEmail(email);
		System.out.println("delUser: " + delUser);
		userMapper.deleteUser(delUser.getId());
		assertNull(userMapper.getUserByEmail(email));
	}

	@Test
	public void testInsertUser() {
		User userInserted = userMapper.getUserByEmail(email);
		System.out.println("InsUser: " + userInserted);
		assertTrue(user.getUserName().equals(userInserted.getUserName()));
		assertTrue(user.getUserEmail().equals(userInserted.getUserEmail()));
		assertTrue(user.getPassword().equals(userInserted.getPassword()));		
	}

	@Test
	public void testGetUserById() {
		User userByEmail = userMapper.getUserByEmail(email);
		User userById = userMapper.getUserById(userByEmail.getId());	
		System.out.println("ById: " + userByEmail);
		assertNotNull(userById);
	}

	@Test
	public void testGetUserByEmail() {
		User userByEmail = userMapper.getUserByEmail(email);
		System.out.println("ByEmail: " + userByEmail);
		assertNotNull(userByEmail);
	}

}
