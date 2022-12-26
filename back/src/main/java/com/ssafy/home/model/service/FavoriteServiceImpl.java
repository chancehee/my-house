package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dao.FavoriteDao;
import com.ssafy.home.model.dto.FavoriteDto;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	FavoriteDao dao;
	
	@Autowired
	public FavoriteServiceImpl(FavoriteDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int insert(FavoriteDto favoriteDto) throws Exception {
		return dao.insert(favoriteDto);
	}

	@Override
	public int delete(int favCode) throws Exception {
		return dao.delete(favCode);
	}

	@Override
	public List<FavoriteDto> select(String userid) throws Exception {
		return dao.select(userid);
	}

	@Override
	public int get(FavoriteDto favoriteDto) throws Exception {
		return dao.get(favoriteDto);
	}
	
}
