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

import com.ssafy.home.model.dto.CommentDto;
import com.ssafy.home.model.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/commentApi")
public class CommentController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	// 해당 게시글 댓글 가져오기 
	@GetMapping("/comment/{article_no}")
	public ResponseEntity<?> getComment(@PathVariable("article_no") int article_no) throws Exception {
		try {
			List<CommentDto> list = commentService.select(article_no);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	// 해당 게시글 댓글 등록하기 
	@PostMapping("/comment/{article_no}")
	public ResponseEntity<?> insert(@RequestBody CommentDto commentDto, @PathVariable("article_no") int article_no){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			commentService.insert(commentDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/delete/{memo_no}")
	public ResponseEntity<?> delete(@RequestBody CommentDto commentDto){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			logger.debug("comment no : {}", commentDto.getMemo_no());
			System.out.println("comment no : {}" + commentDto.getMemo_no());
			commentService.delete(commentDto.getMemo_no());
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
	public ResponseEntity<?> modify(@RequestBody CommentDto commentDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			commentService.modify(commentDto);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@PostMapping("/deleteComments/{article_no}")
	public ResponseEntity<?> deleteComments(@RequestBody int articleNo){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			commentService.deleteComments(articleNo);
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
