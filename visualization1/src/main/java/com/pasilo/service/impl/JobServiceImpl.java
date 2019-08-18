package com.pasilo.service.impl;

import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.pasilo.bean.Job;
import com.pasilo.bean.JobInfo;
import com.pasilo.dao.JobMapper;
import com.pasilo.service.JobService;
import com.pasilo.utils.CityNameUtils;
import com.pasilo.utils.JobNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobMapper jobMapper;

	@Override
	public Option getJobAmount() {
		List<Job> jobs = jobMapper.getAllJobs();

		Option option = new Option();
		option.title("职位数据采集量", "傻逼东西cnm")
				.tooltip(Trigger.item)
				.tooltip().show(true)
				.formatter("{a} <br/>{b} : {c} ({d}%)");
		// 设置option的图例
		option.legend().x(X.center).y(Y.bottom).data();
		// 设置数据值
		Pie pie = new Pie("岗位（个）");
		pie.roseType(RoseType.area)
				.radius(60, 150)
				.center("50%", "50%");

		List<String> legends = new ArrayList<>();
		for (Job count : jobs) {
			Map<String, Object> map = new HashMap<>();
			map.put("value", count.getJobAmount());//填充饼图数据
			String name = CityNameUtils.getCityInCinese(count.getCityName());
			map.put("name", name);//填充饼图数据对应的搜索引擎
			legends.add(name);
			pie.data(map);
		}
		option.legend().data(legends);
		option.series(pie);

		return option;
	}

	@Override
	public Option getJobAmountCompare() {
		List<JobInfo> infos = jobMapper.getJobKindCount();
		Option option = new Option();

		option.title("工作岗位数量对比")
				.title()
				.x(X.center);

		option.tooltip()
				.trigger(Trigger.item)
				.formatter("{a} <br/>{b}岗 : {c} ({d}%)");

		List<String> jobNames = new ArrayList<>();
		for (JobInfo info : infos) {
			String name = JobNameUtils.getJobNameInChinese(info.getJobName());
			jobNames.add(name);
		}
		option.legend()
				.left(X.left)
				.data(jobNames)
				.orient(Orient.vertical);


		Pie pie = new Pie();
		pie.name("工作岗位数据量显示")
				.type(SeriesType.pie)
				.radius("55%")
				.center("50%", "60%")
				.itemStyle().emphasis().shadowBlur(10)
				.shadowOffsetX(0)
				.shadowColor("rgba(0, 0, 0, 0.5)");
		for (JobInfo info : infos) {
			Map<String, Object> map = new HashMap<>();
			String jobName = JobNameUtils.getJobNameInChinese(info.getJobName());
			map.put("value", info.getJobAmount());
			map.put("name", jobName);
			pie.data(map);
		}
		option.series(pie);

		return option;
	}

	@Override
	public Option getJobPayCompare() {
		List<JobInfo> infos = jobMapper.getJobPayCompare();

		Option option = new Option();

		// 设置图标的标题
		option.title("岗位与薪资关系");

		// 设置图标的提示
		option.tooltip().trigger(Trigger.axis)
				.formatter("工作与薪资: <br/>{b} : {c}K元")
				.axisPointer().type(PointerType.cross);

		// 设置canvas的布局
		option.grid().left("13%")
				.right("10%")
				.bottom("3%")
				.containLabel(true);

		// 设置横轴
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value)
				.min(9)
				.axisLabel().formatter("{value}K 元");
		option.xAxis(valueAxis);

		// 设置纵轴
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.type(AxisType.category)
				.axisLine().onZero(false);
		// set data here
		for(JobInfo info:infos){
			String name = JobNameUtils.getJobNameInChinese(info.getJobName());
			categoryAxis.data(name);
		}
		option.yAxis(categoryAxis);

		Line line = new Line();
		line.type(SeriesType.line)
				.smooth(true)
				.lineStyle().normal().barBorderWidth(3)
				.shadowBlur(10)
				.shadowOffsetY(10)
				.shadowColor("rgba(0,0,0,0.4)");
		for(JobInfo info:infos) {
			line.data(info.getAvgPay());
		}
		option.series(line);


		return option;
	}
}
