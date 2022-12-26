package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dao.BoardDao;
import com.ssafy.home.model.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	BoardDao dao;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int insert(BoardDto boardDto) throws Exception {
		return dao.insert(boardDto);
	}

	@Override
	public List<BoardDto> select() throws Exception {
		return dao.select();
	}

	@Override
	public int delete(int article_no) throws Exception {
		return dao.delete(article_no);
	}

	@Override
	public BoardDto get(int article_no) throws Exception {
		return dao.get(article_no);
	}

	@Override
	public int modify(BoardDto boardDto) throws SQLException {
		return dao.modify(boardDto);
	}

}
