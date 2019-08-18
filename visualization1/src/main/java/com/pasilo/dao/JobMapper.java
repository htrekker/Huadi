package com.pasilo.dao;

import com.pasilo.bean.Job;
import com.pasilo.bean.JobInfo;

import java.util.List;

public interface JobMapper {

	List<Job> getAllJobs();

	List<JobInfo> getJobKindCount();

	List<JobInfo> getJobPayCompare();
}
