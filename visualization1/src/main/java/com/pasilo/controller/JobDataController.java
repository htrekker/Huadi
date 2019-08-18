package com.pasilo.controller;

import com.github.abel533.echarts.Option;
import com.pasilo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/job")
public class JobDataController {

	@Autowired
	private JobService jobService;

	@RequestMapping(value = "/count")
	public Option getJobCount(){

		Option option = jobService.getJobAmount();

		return option;
	}

	@RequestMapping(value = "/kind")
	public Option getKindInfo(){
		Option option = jobService.getJobAmountCompare();

		return option;
	}

	@RequestMapping(value = "/compare")
	public Option getJobCompare(){
		Option option = jobService.getJobPayCompare();

		return option;
	}

}
