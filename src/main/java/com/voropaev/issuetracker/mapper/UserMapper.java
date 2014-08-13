package com.voropaev.issuetracker.mapper;

import java.util.List;

import com.voropaev.issuetracker.domain.User;

public interface UserMapper {
	public void insertUser(User user);
	public List<User> getAllUsers();
	public User getUserById(Integer userId);
	public User getUserByEmail(String email);
	//public void updateUser(User user);
	//public void deleteUser(Integer userId);
}
