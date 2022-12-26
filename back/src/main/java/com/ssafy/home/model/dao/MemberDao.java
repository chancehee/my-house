package com.ssafy.home.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.MemberDto;

@Mapper
public interface MemberDao {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public void join(MemberDto memberDto) throws Exception;
	public void modify(MemberDto memberDto) throws Exception;
	public void delete(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
