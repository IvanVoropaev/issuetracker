package com.voropaev.issuetracker.mapper;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.User;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>������ ��� ������� - �������� User</p>
 *
 */
@Repository(value="userMapper")
public interface UserMapper {
	/**
	 *<p>���������� ������ ������������</p>
	 *
	 *@param user ������������
	 */
	public void insertUser(User user);
	/**
	 *<p>������� ������������ �� ��������������</p>
	 *
	 *@param userId ������������� ������������
	 */
	public User getUserById(Integer userId);
	/**
	 *<p>������� ������������ �� ������ email</p>
	 *
	 *@param email email ������������
	 */
	public User getUserByEmail(String email);
	public void deleteUser(Integer id);
}
