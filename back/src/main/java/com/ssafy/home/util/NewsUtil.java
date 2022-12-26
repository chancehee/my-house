package com.ssafy.home.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsUtil {
	final String baseUrl = "http://openapi.naver.com/v1/search/blog.json?query=";
	
	public String search(String clientId, String secret, String _url) {
		HttpURLConnection con = null;
		String result = "";
		
		try {
			URL url = new URL("http://openapi.naver.com/v1/search/blog.json?query=" + _url);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", secret);
			
			int responseCode = con.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				result = readBody(con.getInputStream());
			} else {
				result = readBody(con.getErrorStream());
			} 
		} catch (Exception e) {
			System.out.println("연결 오류 : " + e);
		} finally {
			con.disconnect();
		}
		
		System.out.println(result);
		return result;
	}
	
	public String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		try (BufferedReader br = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			
			System.out.println(responseBody.toString());
			return responseBody.toString();
			
		} catch (IOException e) {
			throw new RuntimeException("API 응답 일기 실패", e);
		}
	}
	
	
}
