package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.dto.CommentDto;

public interface CommentService {

	List<CommentDto> select(int memo_no);
	
	int insert(CommentDto commentDto);
	
	int delete(int memo_no);
	
	int modify(CommentDto commentDto);
	void deleteComments(int article_no) throws SQLException;

}
