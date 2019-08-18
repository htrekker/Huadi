package com.pasilo.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.VisualMap;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.series.Pie;
import com.pasilo.bean.HouseCount;
import com.pasilo.dao.HouseCountDao;
import com.pasilo.service.HouseCountService;
import com.pasilo.utils.CityNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseCountServiceImpl implements HouseCountService {

	private static final String[] cities = {"bj", "北京", "sh", "上海", "gz", "广州", "wh", "武汉", "sz", "深圳", "hz", "杭州"};

	@Autowired
	private HouseCountDao houseCountDao;

	@Override
	public Option getAllHouseCount() {

		List<HouseCount> counts = houseCountDao.getTotalCount();

		Option option = new Option();
		option.title("整体数据采集量", "傻逼东西cnm")
				.tooltip(Trigger.item)
				.tooltip().show(true)
				.formatter("{a} <br/>{b} : {c} ({d}%)");
		// 设置option的图例
		option.legend().x(X.center).y(Y.bottom).data();
		// 设置数据值
		Pie pie = new Pie("数量（套）");
		pie.roseType(RoseType.area)
				.radius(60, 160)
				.center("50%", "50%");

		List<String> legends = new ArrayList<>();
		for (HouseCount count : counts) {
			Map<String, Object> map = new HashMap<>();
			map.put("value", count.getTotalCount());//填充饼图数据
			String name = CityNameUtils.getCityInCinese(count.getCityName());
			map.put("name", name);//填充饼图数据对应的搜索引擎
			legends.add(name);
			pie.data(map);
		}
		option.legend().data(legends);
		option.series(pie);

		return option;
	}
}
