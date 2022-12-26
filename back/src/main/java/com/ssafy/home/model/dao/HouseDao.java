package com.ssafy.home.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.home.model.dto.HouseDto;
import com.ssafy.home.model.dto.HouseDtoFinal;
import com.ssafy.home.model.dto.HouseInfo2Dto;
import com.ssafy.home.model.dto.HouseInfoDto;
import com.ssafy.home.model.dto.NoticeDto;
import com.ssafy.home.model.dto.SidoGugunCodeDto;

@Mapper
public interface HouseDao {
	// 검색 결과 반환  (검색 조건: 동이름, 거래년도, 거래월) 문자열과 숫자 둘다 담기 위해서 type을 Object로 명시 
	List<HouseDto> select(Map<String, Object> map) throws SQLException;
	
	
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
	List<HouseInfo2Dto> select2(Map<String, Object> map) throws SQLException;
	
	HouseDtoFinal getFav(int aptCode) throws SQLException;
}
