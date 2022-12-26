package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dao.CommentDao;
import com.ssafy.home.model.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {

	CommentDao dao;
	
	@Autowired
	public CommentServiceImpl(CommentDao dao) {
		this.dao = dao;
	}
	
	
	
	@Override
	public List<CommentDto> select(int memo_no) {
		return dao.select(memo_no);
	}

	@Override
	public int insert(CommentDto commentDto) {
		return dao.insert(commentDto);
	}

	@Override
	public int delete(int memo_no) {
		return dao.delete(memo_no);
	}

	@Override
	public int modify(CommentDto commentDto) {
		return dao.modify(commentDto);
	}
	
	@Override
	public void deleteComments(int article_no) throws SQLException {
		dao.deleteComments(article_no);
	}
}
