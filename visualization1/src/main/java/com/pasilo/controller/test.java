package com.pasilo.controller;

import com.pasilo.dao.HouseCountDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class test {

	@Autowired
	public static HouseCountDao houseCountDao;

	public static void main(String[] args) {
		System.out.println(houseCountDao.getTotalCount());
	}

}
