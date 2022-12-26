package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dao.HouseDao;
import com.ssafy.home.model.dto.HouseDto;
import com.ssafy.home.model.dto.HouseDtoFinal;
import com.ssafy.home.model.dto.HouseInfo2Dto;
import com.ssafy.home.model.dto.HouseInfoDto;
import com.ssafy.home.model.dto.SidoGugunCodeDto;

@Service
public class HouseServiceImpl implements HouseService {

	HouseDao dao;
	
	@Autowired
	public HouseServiceImpl(HouseDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<HouseDto> select(Map<String, Object> map) throws SQLException {
		return dao.select(map);
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return dao.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return dao.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return dao.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return dao.getAptInDong(dong);
	}

	@Override
	public List<HouseInfo2Dto> select2(Map<String, Object> map) throws Exception {
		return dao.select2(map);
	}
	
	@Override
	public HouseDtoFinal getFav(int aptCode) throws Exception {
		return dao.getFav(aptCode);
	}

}
