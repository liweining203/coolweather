package com.coolweather.app.model;

public class City {

	private int cityId;
	private String cityName;
	private String cityCode;
	private int proviceId;
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getProviceId() {
		return proviceId;
	}
	public void setProviceId(int proviceId) {
		this.proviceId = proviceId;
	}
	
}
