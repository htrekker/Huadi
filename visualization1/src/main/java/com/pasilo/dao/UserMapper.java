package com.pasilo.dao;

import com.pasilo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

//	@Select("select * from users where username=#{username} ")
	User getUserByName(String username);

	void updatePassword(String passwd);

	String getUserByUsername(String username);

	int addUser(User user);

	boolean ifUserExist(String username);
}
