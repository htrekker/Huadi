package com.pasilo.service;

import com.pasilo.bean.User;

public interface UserService {

	boolean passwordCorrect(User user);

	int addUser(User user);
}
