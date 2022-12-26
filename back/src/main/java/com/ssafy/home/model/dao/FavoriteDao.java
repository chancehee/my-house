package com.ssafy.home.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.FavoriteDto;

@Mapper
public interface FavoriteDao {
	// 관심매물 추가
	int insert(FavoriteDto favoriteDto) throws SQLException;
	// 관심매물 삭제
	int delete(int favCode) throws SQLException;
	// 특정 사용자의 모든 관심매물 가져오기
	List<FavoriteDto> select(String userid) throws SQLException;
	// 특정 매물이 관심매물인지 확인
	int get(FavoriteDto favoriteDto) throws SQLException;
}
