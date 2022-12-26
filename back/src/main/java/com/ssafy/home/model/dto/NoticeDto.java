package com.ssafy.home.model.dto;

public class NoticeDto {
	private String user_id;
	private String num;
	private String title;
	private String text;
	private String join_date;
	
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public NoticeDto() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public NoticeDto(String user_id, String num, String title, String text, String join_date) {
		super();
		this.user_id = user_id;
		this.num = num;
		this.title = title;
		this.text = text;
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "NoticeDto [user_id=" + user_id + ", num=" + num + ", title=" + title + ", text=" + text + ", join_date="
				+ join_date + "]";
	}
	
	
	
	
	

	
	
}
