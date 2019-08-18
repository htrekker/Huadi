package com.pasilo.bean;

public class CityRank {

	private String cityName;
	private float avgPay;
	private float jobChance;
	private float rentAfford;
	private float saleAfford;

	public float getSaleAfford() {
		return saleAfford;
	}

	public void setSaleAfford(float saleAfford) {
		this.saleAfford = saleAfford;
	}

	private float square;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public float getAvgPay() {
		return avgPay;
	}

	public void setAvgPay(float avgPay) {
		this.avgPay = avgPay;
	}

	public float getJobChance() {
		return jobChance;
	}

	public void setJobChance(float jobChance) {
		this.jobChance = jobChance;
	}

	public float getRentAfford() {
		return rentAfford;
	}

	public void setRentAfford(float rentAfford) {
		this.rentAfford = rentAfford;
	}

	public float getSquare() {
		return square;
	}

	public void setSquare(float square) {
		this.square = square;
	}
}
