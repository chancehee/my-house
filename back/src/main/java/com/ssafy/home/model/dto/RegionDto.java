package com.ssafy.home.model.dto;

public class RegionDto {
	private String userId;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public RegionDto() {}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public RegionDto(String userId, String sidoName, String gugunName, String dongName) {
		super();
		this.userId = userId;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	
}
