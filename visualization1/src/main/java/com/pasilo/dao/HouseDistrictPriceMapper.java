package com.pasilo.dao;

import com.pasilo.bean.HouseDistrictPrice;

import java.util.List;

public interface HouseDistrictPriceMapper {

	List<HouseDistrictPrice> getDistrictPriceByCity(String cityName);

}
