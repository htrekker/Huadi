package com.pasilo.service.impl;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.series.Bar;
import com.pasilo.bean.PriceAndPay;
import com.pasilo.dao.PriceAndPayMapper;
import com.pasilo.service.PriceAndPayService;
import com.pasilo.utils.CityNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceAndPayServiceImpl implements PriceAndPayService {

	@Autowired
	private PriceAndPayMapper payMapper;

	@Override
	public Option getAllPayInfo() {
		List<PriceAndPay> prices = payMapper.getAllPriceInfo();
		String[] legends = {"买房", "购房"};

		Option option = new Option();

		option.title("10大城市房价对比", "售房与租房");
		//设置option的tooltip
		option.tooltip()
				.axisPointer().type(PointerType.cross)
				.crossStyle().color("#999");
		// 设置tooltip的trigger
		option.tooltip().trigger(Trigger.axis);
		option.legend().data(legends);

		List<String> xs = new ArrayList<>();
		CategoryAxis xAxis = new CategoryAxis();
		for (PriceAndPay price : prices) {
//			System.out.println(price);
			xs.add(CityNameUtils.getCityInCinese(price.getCityName()));
		}
		xAxis.setData(xs);

		ValueAxis valueAxis1 = new ValueAxis();
		valueAxis1.name("租房")
				.min(0).max(120)
				.interval(24)
				.axisLabel()
				.formatter("{value} 元/㎡/月");
		ValueAxis valueAxis2 = new ValueAxis();
		valueAxis2.name("售房")
				.min(0).max(80000)
				.interval(16000)
				.axisLabel()
				.formatter("{value} 元/㎡");

		option.xAxis(xAxis);
		option.yAxis(valueAxis1, valueAxis2);


		Bar bar = new Bar();
		bar.name("售房").type(SeriesType.bar);

		Bar rentBar = new Bar();
		rentBar.name("租房").type(SeriesType.bar);

		List<Float> rent = new ArrayList<>();
		List<Float> sale = new ArrayList<>();
		for (PriceAndPay price : prices) {
			rent.add(price.getRentPrice());
			sale.add(price.getSalePrice());
		}
		bar.setData(sale);
		rentBar.setData(rent);
		bar.yAxisIndex(1);
		rentBar.yAxisIndex(0);

		option.series(rentBar);
		option.series(bar);

		return option;
	}

	@Override
	public Option getAllCityPricePayCompare() {
		List<PriceAndPay> priceAndPays = new ArrayList<>();

		for (PriceAndPay item : payMapper.getAllInformation()) {
//			if (item.getCityName().equals("bj")) {
//				priceAndPays.add(item);
//			}
//			if (item.getCityName().equals("sh")) {
//				priceAndPays.add(item);
//			}
//			if (item.getCityName().equals("gz")) {
//				priceAndPays.add(item);
//			}
//			if (item.getCityName().equals("sz")) {
//				priceAndPays.add(item);
//			}
			priceAndPays.add(item);
		}

		Option option = new Option();

		option.title("城市数据对比")
				.tooltip().trigger(Trigger.axis)
				.axisPointer().type(PointerType.shadow);

		for (PriceAndPay city : priceAndPays) {
			String name = CityNameUtils.getCityInCinese(city.getCityName());
			option.legend().data(name);
		}

		option.grid().left("3%")
				.right("4%")
				.bottom("3%")
				.containLabel(true);

		ValueAxis xAxis = new ValueAxis();
		xAxis.type(AxisType.value);
		option.xAxis(xAxis);

		String[] name = {"租房数据", "售房数据", "收入信息"};
		CategoryAxis yAxis = new CategoryAxis();
		yAxis.type(AxisType.category);
		yAxis.data(name);
		option.yAxis(yAxis);

		for (int i = 0; i < priceAndPays.size(); i++) {
			Bar bar = new Bar();
			String cityName = CityNameUtils.getCityInCinese(priceAndPays.get(i).getCityName());
			bar.name(cityName)
					.type(SeriesType.bar)
					.stack("总量");
			bar.data(priceAndPays.get(i).getRentPrice());
			bar.data(priceAndPays.get(i).getSalePrice() / 1000);
			bar.data(priceAndPays.get(i).getAvgPay());
			option.series(bar);
		}

		return option;
	}
}
