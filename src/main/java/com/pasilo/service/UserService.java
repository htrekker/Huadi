package com.pasilo.service;

import com.pasilo.bean.User;

import java.util.List;

public interface UserService {

	boolean passwordCorrect(User user);

	boolean ifAdmin(User user);

	int addUser(User user);

	int deleteUserByname(String username);

	int updateUser(User user);

	List<User> getUsersInRange(int start, int len);

	User getUserByName(String username);

	int getPageCount(int pageSize);

	int updateUserAdmin(User user);
}
