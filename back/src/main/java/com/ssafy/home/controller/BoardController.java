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

import com.ssafy.home.model.dto.BoardDto;
import com.ssafy.home.model.service.BoardService;

@RestController
@RequestMapping("/boardApi")
@CrossOrigin("*")
public class BoardController {
	
	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardSerivce) {
		this.boardService = boardSerivce;
	}
	
	// 모든 게시글 가져오기 
	@GetMapping("/board")
	public ResponseEntity<?> boardAll() throws Exception {
		System.out.println("게시글 가져오기 시작");
		try {
			List<BoardDto> list = boardService.select();
			if (list != null && !list.isEmpty()) {
				System.out.println("게시글 개수: " + list.size());
				return new ResponseEntity<List<BoardDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<BoardDto>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 특정 게시글 가져오기 
	@GetMapping("/board/{article_no}")
	public ResponseEntity<?> getArticle(@PathVariable("article_no") int article_no) throws Exception {
		return new ResponseEntity<BoardDto>(boardService.get(article_no), HttpStatus.OK);
	}
	
	// 글 작성하기 
	@PostMapping("/board")
	public ResponseEntity<?> wrtieArticle(@RequestBody BoardDto boardDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			boardService.insert(boardDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 게시글 삭제하기 
	@DeleteMapping("/board/{article_no}")
	public ResponseEntity<?> deleteArticle(@PathVariable("article_no") int article_no) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			boardService.delete(article_no);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch(Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	// 게시글 수정하기 
	@PostMapping("/modify")
	public ResponseEntity<?> modifyArticle(@RequestBody BoardDto boardDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			boardService.modify(boardDto);
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
