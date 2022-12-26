package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.dto.BoardDto;

public interface BoardService {

	// 게시글 작성 
	int insert(BoardDto boardDto) throws Exception;
	// 모든 게시글 가져오기 
	List<BoardDto> select() throws Exception;
	// 글 삭제하기 
	int delete(int article_no) throws Exception; 
	// 특정 게시글 가져오기 
	BoardDto get(int article_no) throws Exception;
	// 특정 게시글 수정하기 
	int modify(BoardDto boardDto) throws SQLException;
	
}
