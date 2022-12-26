package com.ssafy.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.FavoriteDto;
import com.ssafy.home.model.service.FavoriteService;

@RestController
@RequestMapping("/favoriteApi")
@CrossOrigin("*")
public class FavoriteController {
	
	private FavoriteService favoriteService;
	
	@Autowired
	public FavoriteController(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}
	
	@GetMapping("/favorite/{userid}")
	public ResponseEntity<?> getAllfavorite(@PathVariable String userid) throws Exception {
		
		try {			
			List<FavoriteDto> list = favoriteService.select(userid);
			if (list != null && !list.isEmpty()) {	
				System.out.println("chan 데이터 가져옴!!!");
				System.out.println(list.size());
				return new ResponseEntity<List<FavoriteDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
		
		
	}
	
	@PostMapping("/favorite")
	public ResponseEntity<?> insert(@RequestBody FavoriteDto favoriteDto) throws Exception {
		System.out.println("관심매물 등록 시작 !!!");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println(favoriteDto.getAptCode());
			favoriteService.insert(favoriteDto);
			System.out.println(favoriteDto.getAptCode());
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", "fail");
			status = HttpStatus.NO_CONTENT;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> check(@RequestBody FavoriteDto favoriteDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		System.out.println("선택된 매물이 관심 목록에 있는가 체크 시작 !!");
		try {
			int result = favoriteService.get(favoriteDto);
			System.out.println("result: " + result);
			if (result == 1) {
				System.out.println("있음");
				resultMap.put("message", "있음");
				status = HttpStatus.OK;
			} else if (result == 0) {
				System.out.println("없음");
				resultMap.put("message", "없음");
				status = HttpStatus.NO_CONTENT;
			}
			
			
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
