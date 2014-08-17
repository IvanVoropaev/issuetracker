package com.voropaev.issuetracker.mapper;

import com.voropaev.issuetracker.domain.User;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>������ ��� ������� - �������� User</p>
 *
 */

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
}
