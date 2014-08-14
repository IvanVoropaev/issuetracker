package com.voropaev.issuetracker.mapper;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.User;

@Repository
public interface UserMapper {
	public void insertUser(User user);
	public User getUserById(Integer userId);
	public User getUserByEmail(String email);
}
