package com.ssafy.home.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.NoticeDto;

@Mapper
public interface NoticeDao {
	// 목록 반환 
	List<NoticeDto> select(String pgNo) throws SQLException;
	
	// 글 등록하기
	int insert(HashMap<String, String> map) throws SQLException;
	
	// 글 삭제하기 (member 테이블 에서 비밀 번호 조회 == 입력한 비밀번호)
	int delete(int num) throws SQLException;
	
	int modify(HashMap<String, String> map) throws SQLException;

	// num 번호와 일치하는 공지사항 가져오기 
	NoticeDto get(int num) throws SQLException;
}
