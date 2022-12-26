package com.ssafy.home.model.dto;

public class BoardDto {
	// 게시글 번호 
	private int article_no;
	// 멤버 아이디 
	private String user_id;
	// 게시글 제목 
	private String title;
	// 게시글 내용 
	private String text;
	// 게시글 조회수 
	private int hit;
	// 게시글 등록시간 
	private String regtime;
	
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	
	
	
	
	public BoardDto() {
		super();
	}
	public BoardDto(int article_no, String user_id, String title, String text, int hit, String regtime) {
		super();
		this.article_no = article_no;
		this.user_id = user_id;
		this.title = title;
		this.text = text;
		this.hit = hit;
		this.regtime = regtime;
	}
	
	
	@Override
	public String toString() {
		return "BoardDto [article_no=" + article_no + ", user_id=" + user_id + ", title=" + title + ", text=" + text
				+ ", hit=" + hit + ", regtime=" + regtime + "]";
	}
	
	
	
	

}
