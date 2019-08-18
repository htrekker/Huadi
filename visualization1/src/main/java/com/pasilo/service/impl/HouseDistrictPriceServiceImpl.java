package com.pasilo.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.VisualMap;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.pasilo.bean.HouseDistrictPrice;
import com.pasilo.dao.HouseDistrictPriceMapper;
import com.pasilo.service.HouseDistrictPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseDistrictPriceServiceImpl implements HouseDistrictPriceService {

	@Autowired
	private HouseDistrictPriceMapper mapper;

	@Override
	public Option getCityHeatMap(String cityName) {
		List<HouseDistrictPrice> prices = mapper.getDistrictPriceByCity(cityName);

		Option option = new Option();
		option.title().text("北京市房价热力图")
				.subtext("傻逼东西cnm");
		option.tooltip().trigger(Trigger.item)
				.formatter("{b}<br/>{c} (元 / ㎡)");

//		Map<String, Object> feature1 = new HashMap<>();
//		DataView dataView = new DataView();
//		dataView.readOnly(false);
//		feature1.put("dataView", dataView);
//		Map<String, Object> feature2 = new HashMap<>();
//		Restore restore = new Restore();
//		feature2.put("restore", restore);
//		Map<String, Object> feature3 = new HashMap<>();
//		SaveAsImage saveAsImage = new SaveAsImage();
//		feature3.put("saveAsImage", saveAsImage);
//
//		Toolbox toolbox = new Toolbox();
//		toolbox.feature(feature1, feature2, feature3)
//				.show(true)
//				.orient(Orient.vertical)
//				.left(X.right)
//				.top(X.center);
//		option.setToolbox(toolbox);

		String[] text = {"高", "低"};
		String[] color = {"orangered", "yellow", "lightskyblue"};
		option.visualMapNew()
				.max(50000)
				.min(800)
				.text(text)
				.realtime(false)
				.color(color)
				.calculable(true);

		com.github.abel533.echarts.series.Map map1 = new com.github.abel533.echarts.series.Map();
		map1.name("北京房价热力图")
				.type(SeriesType.map)
				.mapType("BJ");
		for (HouseDistrictPrice price : prices) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("name", price.getDistrict());
			temp.put("value", price.getAverageSalePrice());
			map1.data(temp);
		}
//		map1.data()

		option.series(map1);
		return option;

	}
}
