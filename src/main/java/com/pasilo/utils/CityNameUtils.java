package com.pasilo.utils;

public class CityNameUtils {
	private static final String[] cities = {"bj", "北京", "sh", "上海", "gz",
			"广州", "wh", "武汉", "sz", "深圳", "hz", "杭州",
			"tj", "天津", "cd", "成都", "nj", "南京", "xa", "西安"};

	public static String getCityInCinese(String city) {
		for (int i = 0; i < cities.length; i = i + 2) {
			if (cities[i].equals(city)) {
				return cities[i + 1];
			}
		}
		return null;
	}

	public static String getCityCode(String cityName) {
		for (int i = 1; i < cities.length; i = i + 2) {
			if (cities[i].equals(cityName)) {
				return cities[i - 1];
			}
		}
		return null;
	}


}
