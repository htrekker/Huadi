package com.pasilo.controller;

import com.github.abel533.echarts.Option;
import com.pasilo.bean.GeoData;
import com.pasilo.service.HouseDistrictPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/district")
public class DistrictDataController {

	@Autowired
	private HouseDistrictPriceService districtPriceService;

	@RequestMapping(value = "/geo")
	public List<GeoData> getCityGeoInfo(){
		List<GeoData> info = districtPriceService.getGeoCitites();

		System.out.println(info);

		return info;
	}


	@RequestMapping(value = "/heat-map", method = RequestMethod.GET)
	public Option getHeatMap(String city, Model model){
		if(city == null){
			city = "bj";
		}
		Option option = districtPriceService.getCityHeatMap(city);

		return option;
	}

	@RequestMapping(value = "/compare", method = RequestMethod.GET)
	public Option getBothinfo(String city){
		System.out.println(city);
		Option option = districtPriceService.getSaleAndRentPriceInBar(city.toLowerCase());

		return option;
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Option getHouseDistrictCount(String city){
		Option option = districtPriceService.getSaleDistrictCount(city);

		return option;
	}

}
