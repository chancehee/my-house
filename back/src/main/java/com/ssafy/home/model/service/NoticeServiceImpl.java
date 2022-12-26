package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dao.NoticeDao;
import com.ssafy.home.model.dto.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService {


	private NoticeDao dao;
	
	@Autowired
	public NoticeServiceImpl(NoticeDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<NoticeDto> select(String pgNo) throws SQLException {
		return dao.select(pgNo);
	}

	@Override
	public int insert(HashMap<String, String> map) throws SQLException {
		return dao.insert(map);
	}

	@Override
	public int delete(int num) throws SQLException {
		return dao.delete(num);
	}
	
	@Override
	public int modify(HashMap<String, String> map) throws SQLException {
		return dao.modify(map);
	}
	
	@Override
	public NoticeDto get(int num) throws SQLException {
		return dao.get(num);
	}

}
