package com.pasilo.dao;

import com.pasilo.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseCountDaoTest {
	@Autowired
	private HouseCountDao countDao;

	@Test
	public void testInsert() throws Exception {
		List list = countDao.getTotalCount();

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
