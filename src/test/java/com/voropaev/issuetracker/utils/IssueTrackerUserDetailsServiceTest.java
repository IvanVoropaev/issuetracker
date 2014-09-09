package com.voropaev.issuetracker.utils;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import com.voropaev.issuetracker.domain.User;
import com.voropaev.issuetracker.mapper.UserMapper;

@RunWith(MockitoJUnitRunner.class)
public class IssueTrackerUserDetailsServiceTest {
	
	@Mock
	UserMapper userMapper;
	
	@InjectMocks
	IssueTrackerUserDetailsService service;
	
	@Test
	public void testLoadUserByUsername() {
		User user = new User();
		String email = "email";
		String password = "password";
		user.setUserEmail(email);
		user.setPassword(password);
		when(userMapper.getUserByEmail(email)).thenReturn(user);
		UserDetails userDetails = service.loadUserByUsername(email);
		assertEquals(email, userDetails.getUsername());
		assertEquals(password, userDetails.getPassword());
	}
}
