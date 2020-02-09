package com.pasilo.controller;


import com.github.abel533.echarts.Option;
import com.pasilo.bean.HouseCount;
import com.pasilo.service.CityRankService;
import com.pasilo.service.HouseCountService;
import com.pasilo.service.HouseDistrictPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/house")
public class HouseDataCtronller {

	@Autowired
	private HouseCountService service;

	@Autowired
	private CityRankService rankService;

	@RequestMapping(value = "/count")
	public Option getCount(){
		Option option = service.getAllHouseCount();

		return option;
	}

	@RequestMapping(value = "/rank")
	public Option getRank(String city){
		Option option = rankService.getRankByCity(city);

		return option;
	}



}
