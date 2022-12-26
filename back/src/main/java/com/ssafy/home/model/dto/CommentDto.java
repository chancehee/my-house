package com.ssafy.home.model.dto;

public class CommentDto {
	
	// 댓글 번호 
	private int memo_no;
	// 유저 아이디 
	private String user_id;
	// 댓글 
	private String comment;
	// 댓글 작성 시간 
	private String memotime;
	// 게시글 번호 
	private int article_no;
	
	public int getMemo_no() {
		return memo_no;
	}
	public void setMemo_no(int memo_no) {
		this.memo_no = memo_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getMemo_time() {
		return memotime;
	}
	public void setMemo_time(String memotime) {
		this.memotime = memotime;
	}
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	
	public CommentDto(int memo_no, String user_id, String comment, String memotime, int article_no) {
		super();
		this.memo_no = memo_no;
		this.user_id = user_id;
		this.comment = comment;
		this.memotime = memotime;
		this.article_no = article_no;
	}
	
	public CommentDto() {
		
	}
	
	@Override
	public String toString() {
		return "CommemDto [memo_no=" + memo_no + ", user_id=" + user_id + ", comment=" + comment + ", memotime="
				+ memotime + ", article_no=" + article_no + "]";
	}
	
	
	
}
