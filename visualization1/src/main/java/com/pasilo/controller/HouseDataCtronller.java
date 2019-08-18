package com.pasilo.controller;


import com.github.abel533.echarts.Option;
import com.pasilo.bean.HouseCount;
import com.pasilo.service.CityRankService;
import com.pasilo.service.HouseCountService;
import com.pasilo.service.HouseDistrictPriceService;
import org.springframework.beans.factory.annotation.Autowired;
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
	private HouseDistrictPriceService districtPriceService;

	@Autowired
	private CityRankService rankService;

	@RequestMapping(value = "/count")
	public Option getCount(){
		Option option = service.getAllHouseCount();

		return option;
	}

	@RequestMapping(value = "/rank")
	public Option getRank(){
		Option option = rankService.getRankByCity("sh");

		return option;
	}

	@RequestMapping(value = "/district-heat-map")
	public Option getHeatMap(@RequestParam(value = "city",required = false) String cityName){
		if(cityName == null){
			cityName = "bj";
		}
		Option option = districtPriceService.getCityHeatMap(cityName);

		return option;
	}


}
