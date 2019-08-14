package com.pasilo.service.impl;

import com.pasilo.bean.User;
import com.pasilo.dao.UserMapper;
import com.pasilo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
			String actual = userMapper.getUserByName(username).getPassword();
			if (password.equals(actual)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int addUser(User user){

		String username = user.getUsername();
		String password = user.getPassword();

		String usernamePattern = "^[\\da-zA-Z\\*#@]{6,25}$";
		String passwdPattern = "^[a-zA-Z\\d][\\dA-Za-z\\-\\*#@]{6,25}$";

		// 检查用户名是否存在
		if(userMapper.ifUserExist(username)){
			return 0;
		}
		// 检查用户输入的用户名，密码是否合法 不合法就返回
		if(!Pattern.matches(usernamePattern, username)){
			return -1;
		}
		if(!Pattern.matches(passwdPattern, password)){
			return -2;
		}

		// 返回插入的状态
		return userMapper.addUser(user);
	}


//	@Bean
//	public UserService getUserService(){
//		return new UserServiceImpl();
//	}

}
