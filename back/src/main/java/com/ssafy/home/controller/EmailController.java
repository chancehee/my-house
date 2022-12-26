package com.ssafy.home.controller;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.home.model.dto.MemberDto;
import com.ssafy.home.model.service.MemberService;
import com.ssafy.home.util.EmailUtil;
import com.ssafy.home.util.EncryptUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
public class EmailController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private final EmailUtil emailUtil;
	private final EncryptUtil encryptUtil;
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/send")
	public ResponseEntity<Map<String, Object>> send(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto user = memberService.userInfo(memberDto.getUserid());
			if (user != null) {
				String tmpPwd = getRandomPassword(10);
				String salt = user.getSalt();
				String tmpHashedPwd = encryptUtil.hashing(tmpPwd.getBytes(), salt);
				user.setUserpwd(tmpHashedPwd);
				memberService.modify(user);
				String email = memberDto.getEmail();
				String title = "[마하] 임시 비밀번호입니다.";
				String contents = "임시 비밀번호: " + tmpPwd;
				emailUtil.sendEmail(email, title, contents);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	private String getRandomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }
}
