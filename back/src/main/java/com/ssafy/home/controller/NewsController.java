package com.ssafy.home.controller;

import java.io.Console;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.home.model.dto.CommentDto;
import com.ssafy.home.util.NewsUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/server")
public class NewsController {
	
	
	@GetMapping("/naver/{query}")
	public ResponseEntity<?> getComment(@PathVariable("query") String query) throws Exception {
		try {
			ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
			String encode = StandardCharsets.UTF_8.decode(buffer).toString();
			
			URI uri = UriComponentsBuilder
					.fromUriString("https://openapi.naver.com")
					.path("/v1/search/news.json")
					.queryParam("query", encode)
					.queryParam("display", 10)
					.queryParam("start", 1)
					.queryParam("sort", "date")
					.encode()
					.build()
					.toUri();
			
			System.out.println(uri);
			
			RestTemplate restTamplate = new RestTemplate();
			
			RequestEntity<Void> req = RequestEntity
					.get(uri)
					.header("X-Naver-Client-Id", "ssZGTR4VEdWE_nWNn8wR")
					.header("X-Naver-Client-Secret", "37B1VcFdDa")
					.build();
			
			ResponseEntity<String> result = restTamplate.exchange(req, String.class);
			System.out.println(result);
			
			return new ResponseEntity<>(result.getBody(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("에러남",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
