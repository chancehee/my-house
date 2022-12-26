package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.home.model.dto.NoticeDto;



public interface NoticeService {
	List<NoticeDto> select(String pgNo) throws SQLException;
	
	int insert(HashMap<String, String> map) throws SQLException;
	
	int delete(int num) throws SQLException;
	int modify(HashMap<String, String> map) throws SQLException;
	NoticeDto get(int num) throws SQLException;
}
