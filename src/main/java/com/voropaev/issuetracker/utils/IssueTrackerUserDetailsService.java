package com.voropaev.issuetracker.utils;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.voropaev.issuetracker.mapper.UserMapper;

public class IssueTrackerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		com.voropaev.issuetracker.domain.User userEntity = userMapper.getUserByEmail(email);
		System.out.println(userEntity);
		if (userEntity == null) {
			System.out.println("Error");
			throw new UsernameNotFoundException("user not found");
		}
			
		UserDetails userDetails = new User (userEntity.getUserEmail().trim(), 
											userEntity.getPassword().trim(),
											true,
											true,
											true,
											true,
											new HashSet<GrantedAuthority>());
		return userDetails;
	}

}
