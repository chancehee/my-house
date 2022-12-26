package com.ssafy.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssafy.home.model.dto.MemberDto;

@Controller
public class MainController {
	
	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/noticeList")
	public String noticeList() {
		return "notice/noticeList";
	}
	
	// 공지사항 등록 페이지로 이동 
	@GetMapping("/noticeRegisterPage")
	public String noticeRegist() {
		return "notice/notice";
	}
	
	// 회원가입 페이지로 이동 
	@GetMapping("/userJoinPage")
	public String userJoin() {
		return "user/join";
	}
	
	// 로그인 페이지로 이동 
	@GetMapping("/userLoginPage")
	public String userLogin() {
		return "user/login";
	}
	
	// 관리자 모드 -> 회원 목록 페이지 이동 
	@GetMapping("/userSearchPage")
	public String userSearch() {
		return "user/search";
	}
	
	@GetMapping("/favoritePage")
	public String regjionPage() {
		return "region/region";
	}
	
	@GetMapping("find")
	public String findPage(MemberDto user, HttpServletRequest request) {
	    return "/user/find"; 
	}
	
}
