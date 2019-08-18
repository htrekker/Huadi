package com.pasilo.bean;

public class PriceAndPay {

	private String cityName;
	private float salePrice;
	private float rentPrice;
	private float avgPay;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public float getAvgPay() {
		return avgPay;
	}

	public void setAvgPay(float avgPay) {
		this.avgPay = avgPay;
	}

	@Override
	public String toString() {
		return "PriceAndPay{" +
				"cityName='" + cityName + '\'' +
				", salePrice=" + salePrice +
				", rentPrice=" + rentPrice +
				", avgPay=" + avgPay +
				'}';
	}
}
