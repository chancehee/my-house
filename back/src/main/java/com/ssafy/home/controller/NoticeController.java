package com.ssafy.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.NoticeDto;
import com.ssafy.home.model.service.NoticeService;

@RestController
@RequestMapping("/noticeApi")
@CrossOrigin("*")
public class NoticeController {
	
	public static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping("/notice/{pgNo}")
	public ResponseEntity<?> noticeAll(@PathVariable("pgNo") String pgNo){
		try {
			List<NoticeDto> list = noticeService.select(pgNo);
			for(NoticeDto data : list)
				System.out.println(data);
			if (list != null && !list.isEmpty()) {
				System.out.println("공지사항 개수: " + list.size());
				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// get notice 
	@GetMapping("/noticeDetail/{num}")
	public ResponseEntity<?> getNotice(@PathVariable("num") String num) {
		System.out.println("getNotice내부 입니다!!!");
		try {
			NoticeDto notice = noticeService.get(Integer.parseInt(num));
			
			if (notice != null) {
				System.out.println("값을 잘 가져왔습니다!! ");
				return new ResponseEntity<NoticeDto>(notice, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// delete notice 
	@DeleteMapping("/notice/{num}/{pgNo}")
	public ResponseEntity<?> deleteNotice(@PathVariable Map<String, String> pathVarsMap){
		String num  = pathVarsMap.get("num");
		String pgNo = pathVarsMap.get("pgNo");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			noticeService.delete(Integer.parseInt(num));
			resultMap.put("message",  SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/notice")
	private ResponseEntity<?> insertNotice(@RequestBody HashMap<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			noticeService.insert(map);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/modify")
	private ResponseEntity<?> modifyNotice(@RequestBody HashMap<String, String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			noticeService.modify(map);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
