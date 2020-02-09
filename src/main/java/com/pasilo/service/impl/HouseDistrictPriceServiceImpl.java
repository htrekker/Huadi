package com.pasilo.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.VisualMap;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.feature.DataView;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.Restore;
import com.github.abel533.echarts.feature.SaveAsImage;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.pasilo.bean.CityWithLocation;
import com.pasilo.bean.GeoData;
import com.pasilo.bean.HouseDistrictPrice;
import com.pasilo.dao.CityLocationMapper;
import com.pasilo.dao.HouseDistrictPriceMapper;
import com.pasilo.service.HouseDistrictPriceService;
import com.pasilo.utils.CityNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseDistrictPriceServiceImpl implements HouseDistrictPriceService {

	@Autowired
	private HouseDistrictPriceMapper mapper;

	@Autowired
	private CityLocationMapper locationMapper;

	@Override
	public List<GeoData> getGeoCitites() {

		List<CityWithLocation> cities = locationMapper.getGeoCities();

		List<GeoData> infos = new ArrayList<>();
		for (CityWithLocation city : cities) {
			GeoData info = new GeoData();
			float[] nums = new float[3];
			nums[1] = city.getLongitude();
			nums[0] = city.getLatitude();
			nums[2] = city.getNum();
			String name = CityNameUtils.getCityInCinese(city.getCity());
			info.setName(name);
			info.setValue(nums);
			infos.add(info);
		}

		return infos;
	}

	@Override
	public Option getCityHeatMap(String cityName) {
		cityName = cityName.toLowerCase();
		List<HouseDistrictPrice> prices = mapper.getDistrictPriceByCity(cityName);

		String name = CityNameUtils.getCityInCinese(cityName);
		Option option = new Option();
		option.title().text(name + "市房价热力图");
//				.subtext("傻逼东西cnm");
		option.tooltip().trigger(Trigger.item)
				.formatter("{b}<br/>{c} (元 / ㎡)");

		String[] text = {"高", "低"};
		String[] color = {"orangered", "yellow", "lightskyblue"};
		option.visualMapNew()
				.max(65000)
				.min(7000)
				.text(text)
				.realtime(false)
				.color(color)
				.calculable(true);

		com.github.abel533.echarts.series.Map map1 = new com.github.abel533.echarts.series.Map();
		map1.name(name + "房价热力图")
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

	@Override
	public Option getSaleAndRentPriceInBar(String name) {
		name = name.toLowerCase();
		List<HouseDistrictPrice> districts = mapper.getDistrictPriceByCity(name);

		Option option = new Option();

		String cityName = CityNameUtils.getCityInCinese(name);
		option.title().text(cityName + "租售房价格对比");

		option.tooltip().trigger(Trigger.axis)
				.axisPointer().type(PointerType.cross)
				.crossStyle().color("#999");

		option.legend().data("售房").data("租房");

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.type(AxisType.category);
		// set data here
		for (HouseDistrictPrice district : districts) {
			xAxis.data(district.getDistrict());
		}
		option.xAxis(xAxis);

		Map<String, Float> saleRange = mapper.getSaleRange(name);

		int interval = 2;
		double min = Math.floor(saleRange.get("min") / 10000);
		double max = Math.floor(saleRange.get("max") / 10000) + 1;
		if (max - min < 5) {
			interval = 1;
		}
		ValueAxis yAxis1 = new ValueAxis();
		yAxis1.type(AxisType.value)
				.name("售房")
				.min(min).max(max)
				.interval(interval)
				.axisLabel().formatter("{value} 万元/㎡");

		Map<String, Float> rentRange = mapper.getRentMinAndMAx(name);

		int rentInterval = 20;
		double rentMin = Math.floor(rentRange.get("min"));
		double rentMax = Math.ceil(rentRange.get("max")) + 1;
		ValueAxis yAxis2 = new ValueAxis();
		yAxis2.type(AxisType.value)
				.name("租房")
				.min(rentMin).max(rentMax)
				.interval(rentInterval)
				.axisLabel().formatter("{value} 元/月/㎡");
		option.yAxis(yAxis1, yAxis2);

		// 售房信息
		Bar bar = new Bar();
		bar.type(SeriesType.bar)
				.name("售房").yAxisIndex(0);
		for (HouseDistrictPrice district : districts) {
			bar.data(district.getAverageSalePrice() / 10000);
		}

		// 租房信息
		Line line = new Line();
		line.type(SeriesType.line)
				.name("租房").yAxisIndex(1);
		for (HouseDistrictPrice districtPrice : districts) {
			line.data(districtPrice.getAverageRentPrice());
		}

		option.series(bar, line);

		return option;
	}

	@Override
	public Option getSaleDistrictCount(String name) {
		name = name.toLowerCase();
		List<HouseDistrictPrice> counts = mapper.getDistrictAmountByCity(name);
		System.out.println("code:  " + name);


		Option option = new Option();
		String cityName = CityNameUtils.getCityInCinese(name);
		System.out.println("chinesse:  " + cityName);
		option.title().text(cityName + "各区租售房数量对比")
				.x(X.center);

		option.tooltip().trigger(Trigger.item)
				.formatter("{a} <br/>{b} : {c} ({d}%)");

		option.legend().x(X.center)
				.y(Y.bottom);
		for (HouseDistrictPrice count : counts) {
			option.legend().data(count.getDistrict());
		}

		option.calculable(true);

		Pie rentPie = new Pie();
		rentPie.name("租房数量")
				.radius(20, 110)
				.center("25%", "50%")
				.roseType(RoseType.radius);
		//set data here
		for (HouseDistrictPrice count : counts) {
			Map<String, Object> map = new HashMap<>();
			map.put("value", count.getRentAmount());
			map.put("name", count.getDistrict());

			rentPie.data(map);
		}

		Pie salePie = new Pie();
		salePie.name("售房数量")
				.radius(30, 110)
				.center("75%", "50%")
				.roseType(RoseType.area);
		// set data here
		for (HouseDistrictPrice count : counts) {
			Map<String, Object> map = new HashMap<>();
			map.put("value", count.getSaleAmount());
			map.put("name", count.getDistrict());

			salePie.data(map);
		}

		option.series(rentPie, salePie);
		return option;
	}


}
