package com.pasilo.bean;

public class JobInfo {

	private String cityName;
	private String jobName;
	private int jobAmount;
	private float avgPay;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobAmount() {
		return jobAmount;
	}

	public void setJobAmount(int jobAmount) {
		this.jobAmount = jobAmount;
	}

	public float getAvgPay() {
		return avgPay;
	}

	public void setAvgPay(float avgPay) {
		this.avgPay = avgPay;
	}

	@Override
	public String toString() {
		return "JobInfo{" +
				"cityName='" + cityName + '\'' +
				", jobName='" + jobName + '\'' +
				", jobAmount='" + jobAmount + '\'' +
				", avgPay='" + avgPay + '\'' +
				'}';
	}
}
