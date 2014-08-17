package com.voropaev.issuetracker.mapper;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.User;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>Маппер для объекта - сущности User</p>
 *
 */
@Repository(value="userMapper")
public interface UserMapper {
	/**
	 *<p>Добавление нового пользователя</p>
	 *
	 *@param user пользователь
	 */
	public void insertUser(User user);
	/**
	 *<p>Выборка пользователя по идентификатору</p>
	 *
	 *@param userId идентификатор пользователя
	 */
	public User getUserById(Integer userId);
	/**
	 *<p>Выборка пользователя по адресу email</p>
	 *
	 *@param email email пользователя
	 */
	public User getUserByEmail(String email);
	public void deleteUser(Integer id);
}
