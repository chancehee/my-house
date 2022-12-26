package com.ssafy.home.model.service;

import com.ssafy.home.model.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public void join(MemberDto memberDto) throws Exception;
	public void modify(MemberDto memberDto) throws Exception;
	public void delete(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}