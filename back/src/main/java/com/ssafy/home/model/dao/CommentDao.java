package com.ssafy.home.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.CommentDto;

@Mapper
public interface CommentDao {
	
	List<CommentDto> select(int memo_no);
	
	int insert(CommentDto commentDto);
	
	int delete(int memo_no);
	
	int modify(CommentDto commentDto);
	
	void deleteComments(int article_no);
}
