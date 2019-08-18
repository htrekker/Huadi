package com.pasilo.controller;

import com.github.abel533.echarts.Option;
import com.pasilo.service.PriceAndPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pricepay")
public class PricePayDataController {

	@Autowired
	private PriceAndPayService priceAndPayService;

	@RequestMapping(value = "/all")
	public Option getAllInfo(){
		Option option = priceAndPayService.getAllPayInfo();

		return option;
	}

	@RequestMapping(value = "/compare")
	public Option getMainCitiesCompare(){
		Option option = priceAndPayService.getAllCityPricePayCompare();

		return option;
	}
}
