package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.home.model.dto.HouseDto;
import com.ssafy.home.model.dto.HouseDtoFinal;
import com.ssafy.home.model.dto.HouseInfo2Dto;
import com.ssafy.home.model.dto.HouseInfoDto;
import com.ssafy.home.model.dto.SidoGugunCodeDto;

public interface HouseService {

	List<HouseDto> select(Map<String, Object> map) throws SQLException;
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
	List<HouseInfo2Dto> select2(Map<String, Object> map) throws Exception;
	HouseDtoFinal getFav(int aptCode) throws Exception;

}
