package com.pasilo.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Radar;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.code.X;
import com.github.abel533.echarts.series.RadarSeries;
import com.pasilo.bean.CityRank;
import com.pasilo.dao.CityDao;
import com.pasilo.service.CityRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CityRankServiceImpl implements CityRankService {

	private static final String[] indicators = {"就业机会", "薪资水平", "购房成本", "租房成本", "居住面积"};
	private static final String[] cities = {"sh", "hz", "gz", "sz", "cd", "wh", "hz"};
	@Autowired
	private CityDao cityDao;

	private boolean ifCityExisted(String city) {
		boolean flag = false;
		for (int i = 0; i < cities.length; i++) {
			if (cities[i].equals(city)) {
				flag = true;
				break;
			}
		}

		return flag;
	}

	public Option getChinaMap() {

		Option option = new Option();
		option.title().left(X.center)
				.text("全国统计数据一览")
				.subtext("Top 10");

		option.tooltip().trigger(Trigger.item);



		return null;
	}

	@Override
	public Option getRankByCity(String cityName) {
		CityRank rank = cityDao.getRankByCity(cityName);

		Option option = new Option();
		option.title("城市宜居度分析", "上海");

		String[] cities = {"上海"};
		int[] paddings = {3, 5};
		option.legend().data(cities);
		Radar radar = new Radar();
		radar.name().textStyle()
//				.backgroundColor("#999")
				.color("#111")
				.borderRadius(3)
				.padding(paddings);

		for (int i = 0; i < indicators.length; i++) {
			radar.indicator(new Radar.Indicator()
					.name(indicators[i])
					.max(10));
		}
		option.radar(radar);

		List<Float> values = new ArrayList<>();
		values.add(rank.getJobChance());
		values.add(rank.getAvgPay());
		values.add(rank.getRentAfford());
		values.add(rank.getRentAfford());
		values.add(rank.getSquare());
		Map<String, Object> map = new HashMap<>();

		map.put("value", values);
		map.put("name", rank.getCityName());

		RadarSeries series = new RadarSeries();
		series.data(map);
		option.series(series);

		return option;
	}
}
