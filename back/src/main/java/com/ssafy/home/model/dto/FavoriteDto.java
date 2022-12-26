package com.ssafy.home.model.dto;

public class FavoriteDto {
	private int favCode;
	private String userid;
	private String aptCode;
	
	public int getFavCode() {
		return favCode;
	}
	
	public void setFavCode(int favCode) {
		this.favCode = favCode;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getAptCode() {
		return aptCode;
	}
	
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	
	public FavoriteDto() {
		super();
	}
	
	public FavoriteDto(int favCode, String userid, String aptCode) {
		super();
		this.favCode = favCode;
		this.userid = userid;
		this.aptCode = aptCode;
	}
	
	@Override
	public String toString() {
		return "FavoriteDto [favCode=" + favCode + ", userid=" + userid + ", aptCode=" + aptCode + "]";
	}
}
