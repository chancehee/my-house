package com.ssafy.home.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.BoardDto;

@Mapper
public interface BoardDao {

	// 게시글 작성 
	int insert(BoardDto boardDto) throws SQLException;
	// 모든 게시글 가져오기 
	List<BoardDto> select() throws SQLException;
	// 글 삭제하기 
	int delete(int article_no) throws SQLException; 
	// 특정 게시글 가져오기 
	BoardDto get(int article_no) throws SQLException;
	// 특정 게시글 수정하기 
	int modify(BoardDto boardDto) throws SQLException;
}
