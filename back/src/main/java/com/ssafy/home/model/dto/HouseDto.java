package com.ssafy.home.model.dto;

// 동이름, 년도, 월 -> houseinfo, housedeal join -> 조회 -> 필요한 정보를 담은 dto 

public class HouseDto {
	
	// housedeal 정보
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	
	// houseinfo 정보 
	private int buildYear;
	private String roadName;
	private String dong;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
	
	
	
	
	public String getDealAmount() {
		return dealAmount;
	}


	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}


	public int getDealYear() {
		return dealYear;
	}


	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}


	public int getDealMonth() {
		return dealMonth;
	}


	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}


	public int getDealDay() {
		return dealDay;
	}


	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getFloor() {
		return floor;
	}


	public void setFloor(String floor) {
		this.floor = floor;
	}


	public int getBuildYear() {
		return buildYear;
	}


	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}


	public String getRoadName() {
		return roadName;
	}


	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}


	public String getDong() {
		return dong;
	}


	public void setDong(String dong) {
		this.dong = dong;
	}


	public String getApartmentName() {
		return apartmentName;
	}


	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}


	public String getJibun() {
		return jibun;
	}


	public void setJibun(String jibun) {
		this.jibun = jibun;
	}


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public HouseDto(String dealAmount, int dealYear, int dealMonth, int dealDay, String area, String floor,
			int buildYear, String roadName, String dong, String apartmentName, String jibun, String lng, String lat) {
		super();
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.dong = dong;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}


	@Override
	public String toString() {
		return "HouseDto [dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", buildYear=" + buildYear
				+ ", roadName=" + roadName + ", dong=" + dong + ", apartmentName=" + apartmentName + ", jibun=" + jibun
				+ ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	
	
}
