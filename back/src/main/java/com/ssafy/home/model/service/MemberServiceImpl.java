package com.ssafy.home.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.home.model.dto.MemberDto;
import com.ssafy.home.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	
	MemberDao memberDao;

	@Autowired
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {		
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberDao.login(memberDto);
	}
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		memberDao.join(memberDto);
	}

	@Override
	public void modify(MemberDto memberDto) throws Exception {
		memberDao.modify(memberDto);
	}
	
	@Override
	public void delete(MemberDto memberDto) throws Exception {
		memberDao.delete(memberDto);
	}

	
	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberDao.userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		memberDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return memberDao.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		memberDao.deleteRefreshToken(map);
	}

}
