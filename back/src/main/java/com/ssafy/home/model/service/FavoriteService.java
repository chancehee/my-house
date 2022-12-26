package com.ssafy.home.model.service;

import java.util.List;

import com.ssafy.home.model.dto.FavoriteDto;

public interface FavoriteService {
	// 관심매물 추가
	int insert(FavoriteDto favoriteDto) throws Exception;
	// 관심매물 삭제
	int delete(int favCode) throws Exception;
	// 특정 사용자의 모든 관심매물 가져오기
	List<FavoriteDto> select(String userid) throws Exception;
	// 특정 매물이 관심매물인지 확인
	int get(FavoriteDto favoriteDto) throws Exception;
}
