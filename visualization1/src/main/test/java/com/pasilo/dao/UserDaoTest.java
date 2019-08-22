package com.pasilo.dao;

import com.pasilo.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@MapperScan("com.pasilo.dao")
public class UserDaoTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.addUser(new User("aa1", "a123456"));
		userMapper.addUser(new User("bb1", "b123456"));
		userMapper.addUser(new User("cc1", "b123456"));

	}

//	@Test
//	public void testQuery() throws Exception {
//		List<User> users = userMapper.getAll();
//		System.out.println(users.toString());
//	}
//

	@Test
	public void testUpdate() throws Exception {
//		userMapper.updatePassword("123");
	}
}