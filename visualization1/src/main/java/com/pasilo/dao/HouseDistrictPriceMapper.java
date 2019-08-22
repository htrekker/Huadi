package com.pasilo.dao;

import com.pasilo.bean.HouseDistrictPrice;

import java.util.List;
import java.util.Map;

public interface HouseDistrictPriceMapper {

	List<HouseDistrictPrice> getDistrictPriceByCity(String cityName);

	List<HouseDistrictPrice> getDistrictAmountByCity(String cityName);

	Map<String, Float> getRentMinAndMAx(String cityName);

	Map<String, Float> getSaleRange(String city);
}
