package com.pasilo.dao;

import com.pasilo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

	//	@Select("select * from users where username=#{username} ")
	User getUserByName(String username);

	void updatePassword(String username, String password);

//	String getUserByUsername(String username);

	int addUser(User user);

	boolean ifUserExist(String username);

	int deleteUser(String username);

	boolean ifAdminExist(String username);

	User getAdminByName(String username);

	List<User> getUserInRange(int start, int len);

	int getUserCount();

	int updateUserAdmin(User user);
}
