package com.voropaev.issuetracker.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.User;

@Repository
public interface UserMapper {
	
	final String INSERT = "INSERT INTO users (user_name,user_email,password) "
							+ "VALUES (#{userName},#{userEmail},#{password})";
	final String SELECT_BY_ID = "SELECT * FROM users WHERE user_id = #{id}"; 
	final String SELECT_BY_EMAIL = "SELECT * FROM users WHERE user_email = #{userEmail}";
	
	@Insert(INSERT)
	public void insertUser(User user);
	
	@Select(SELECT_BY_ID)
	@Results(value = {
			@Result(property="id", column="user_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="userEmail", column="user_email"),
			@Result(property="password", column="password"),
	})
	public User getUserById(Integer userId);
	
	@Select(SELECT_BY_EMAIL)
	@Results(value = {
			@Result(property="id", column="user_id"),
			@Result(property="userName", column="user_name"),
			@Result(property="userEmail", column="user_email"),
			@Result(property="password", column="password"),
	})
	public User getUserByEmail(String email);
}
