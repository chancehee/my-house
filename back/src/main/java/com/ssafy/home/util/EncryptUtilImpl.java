package com.ssafy.home.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtilImpl implements EncryptUtil {
	private static final int SALT_SIZE = 16;

	@Override
    public String makeSalt() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] tmp = new byte[SALT_SIZE];
        rnd.nextBytes(tmp);
        return byteToString(tmp);  
    }
    
	@Override
    public String hashing(byte[] password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시함수를 사용 
 
        // key-stretching
        for(int i = 0; i < 10000; i++) {
            String tmp = byteToString(password) + salt; // 패스워드와 Salt 를 합쳐 새로운 문자열 생성 
            md.update(tmp.getBytes());                  // temp 의 문자열을 해싱하여 md 에 저장해둔다 
            password = md.digest();                      // md 객체의 다이제스트를 얻어 password 를 갱신한다 
        }
        return byteToString(password);
    }
    
    private String byteToString(byte[] tmp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : tmp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }
}
