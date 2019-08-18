package com.pasilo.service;

import com.github.abel533.echarts.Option;
import com.pasilo.bean.CityRank;

public interface CityRankService {

	Option getRankByCity(String cityName);

}
