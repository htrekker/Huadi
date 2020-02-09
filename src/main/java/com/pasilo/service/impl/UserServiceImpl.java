package com.pasilo.service.impl;

import com.pasilo.bean.User;
import com.pasilo.dao.UserMapper;
import com.pasilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
//@Component
public class UserServiceImpl implements UserService {

	@Autowired
	public UserMapper userMapper;

	@Override
	public boolean passwordCorrect(User user) {
		String password = user.getPassword();
		String username = user.getUsername();

		if (password != null && username != null) {// 密码和用户名传入不能为空
			User actual = userMapper.getUserByName(username);
//			System.out.println(actual.getPassword());
			if (actual != null) {
				String actualPassword = actual.getPassword();
				if (password.equals(actualPassword)) return true;
			}
		}

		return false;
	}

	@Override
	public boolean ifAdmin(User user) {
		String password = user.getPassword();
		String username = user.getUsername();

		System.out.println(password + username);

		if (password != null && username != null) {// 密码和用户名传入不能为空
			System.out.println("empty password and username");
			User actual = userMapper.getAdminByName(username);
			if (actual != null) {
				String actualPassword = actual.getPassword();
				if (password.equals(actualPassword)) return true;
			}
		}
		return false;
	}

	@Override
	public int addUser(User user) {

		String username = user.getUsername();
		String password = user.getPassword();

		String usernamePattern = "^[\\da-zA-Z\\*#@]{6,25}$";
		String passwdPattern = "^[a-zA-Z\\d][\\dA-Za-z\\-\\*#@]{6,25}$";

		// 检查用户名是否存在
		if (userMapper.ifUserExist(username)) {
			return 0;
		}
		// 检查用户输入的用户名，密码是否合法 不合法就返回
		if (!Pattern.matches(usernamePattern, username)) {
			return -1;
		}
		if (!Pattern.matches(passwdPattern, password)) {
			return -2;
		}

		// 返回插入的状态
		return userMapper.addUser(user);
	}

	@Override
	public int deleteUserByname(String username) {
		String noPattern = "\\s*\\*?like.%";
		if (Pattern.matches(noPattern, username)) {
			return -2;
		}
		if (userMapper.ifUserExist(username)) {
			userMapper.deleteUser(username);
			return 0;
		}

		return -1;
	}

	@Override
	public int updateUser(User user) {
		userMapper.updatePassword(user.getUsername(), user.getPassword());
		return 0;
	}

	@Override
	public List<User> getUsersInRange(int start, int len) {

		List<User> users = userMapper.getUserInRange(0, 10);

		return users;
	}

	@Override
	public User getUserByName(String username) {
		System.out.println("user service impl:"+username);
		if (username != null) {
			return userMapper.getUserByName(username);
		}

		return null;
	}

	@Override
	public int getPageCount(int pageSize) {
		int amount = userMapper.getUserCount();

		int maxPage = amount % pageSize == 0 ? amount / pageSize : amount / pageSize + 1;

		return maxPage;
	}

	@Override
	public int updateUserAdmin(User user) {

		int code = userMapper.updateUserAdmin(user);

		return 0;
	}


}
