package com.pasilo.bean;

public class CityWithLocation {

	private String city;
	private float longitude;
	private float latitude;
	private float num;

	public String getCity() {
		return city;
	}

	public void setCity(String cityName) {
		this.city = cityName;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getNum() {
		return num / 1000;
	}

	public void setNum(int count) {
		this.num = count;
	}
}
