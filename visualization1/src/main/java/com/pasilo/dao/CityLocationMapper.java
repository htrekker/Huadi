package com.pasilo.dao;

import com.pasilo.bean.CityWithLocation;

import java.util.ArrayList;
import java.util.List;

public interface CityLocationMapper {

	List<CityWithLocation> getGeoCities();
}
