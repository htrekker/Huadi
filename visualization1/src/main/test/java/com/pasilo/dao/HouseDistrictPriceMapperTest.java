package com.pasilo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseDistrictPriceMapperTest {

	@Autowired
	HouseDistrictPriceMapper mapper;

	@Test
	public void testGetMapper(){
		Map<String, Float> result = mapper.getRentMinAndMAx("bj");
		System.out.println(result);
	}


	@Test
	public void testSaleMapper(){
		Map<String, Float> result = mapper.getSaleRange("bj");
		System.out.println(result);
	}

}
