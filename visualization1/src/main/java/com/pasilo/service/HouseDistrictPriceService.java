package com.pasilo.service;

import com.github.abel533.echarts.Option;
import com.pasilo.bean.GeoData;

import java.util.List;
import java.util.Map;

public interface HouseDistrictPriceService {

	List<GeoData> getGeoCitites();

	Option getCityHeatMap(String cityName);

	Option getSaleAndRentPriceInBar(String name);

	Option getSaleDistrictCount(String name);

}
