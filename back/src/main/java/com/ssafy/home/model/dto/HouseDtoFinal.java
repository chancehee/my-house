package com.ssafy.home.model.dto;

import java.math.BigInteger;

public class HouseDtoFinal {

	// dongCode 정보 
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	// housedeal 정보
	private BigInteger no;
	private int dealYear;
	private int dealMonth;
	private String dealAmount;
	private int dealDay;
	private String area;
	private String floor;
		
	// houseinfo 정보 
	private int buildYear;
	private String roadName;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
	
	
	
	public BigInteger getNo() {
		return no;
	}

	public void setNo(BigInteger no) {
		this.no = no;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
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

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
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

	public HouseDtoFinal() {
		super();
	}

	public HouseDtoFinal(String sidoName, String gugunName, String dongName, BigInteger no, int dealYear, int dealMonth,
			String dealAmount, int dealDay, String area, String floor, int buildYear, String roadName,
			String apartmentName, String jibun, String lng, String lat) {
		super();
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.no = no;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealAmount = dealAmount;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "HouseDtoFinal [sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName=" + dongName + ", no="
				+ no + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealAmount=" + dealAmount
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", buildYear=" + buildYear
				+ ", roadName=" + roadName + ", apartmentName=" + apartmentName + ", jibun=" + jibun + ", lng=" + lng
				+ ", lat=" + lat + "]";
	}


	
}
