package com.pasilo.bean;

public class HouseDistrictPrice {

	private String cityName;
	private String district;
	private float averageSalePrice;
	private float averageRentPrice;
	private int saleAmount;
	private int rentAmount;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public float getAverageSalePrice() {
		return averageSalePrice;
	}

	public void setAverageSalePrice(float averageSalePrice) {
		this.averageSalePrice = averageSalePrice;
	}

	public float getAverageRentPrice() {
		return averageRentPrice;
	}

	public void setAverageRentPrice(float averageRentPrice) {
		this.averageRentPrice = averageRentPrice;
	}

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

	public int getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(int rentAmount) {
		this.rentAmount = rentAmount;
	}
}
