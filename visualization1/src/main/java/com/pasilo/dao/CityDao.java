package com.pasilo.dao;

import com.pasilo.bean.CityRank;
import com.pasilo.bean.Job;

import java.util.List;

public interface CityDao {

	public List<Job> getAllJobs();

	public CityRank getRankByCity(String cityName);


}
