package com.pasilo.bean;

public class HouseCount {

	private String cityName;
	private int saleCount;
	private int rentCount;
	private int totalCount = 0;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public int getRentCount() {
		return rentCount;
	}

	public void setRentCount(int rentCount) {
		this.rentCount = rentCount;
	}

	public int getTotalCount(){
		if (totalCount == 0){
			totalCount = rentCount + saleCount;
		}
		return totalCount;
	}

	@Override
	public String toString() {
		return "HouseCount{" +
				"cityName='" + cityName + '\'' +
				", saleCount=" + saleCount +
				", rentCount=" + rentCount +
				'}';
	}
}
