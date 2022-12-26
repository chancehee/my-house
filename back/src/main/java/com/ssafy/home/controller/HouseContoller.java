package com.ssafy.home.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ssafy.home.model.dto.HouseDto;
import com.ssafy.home.model.dto.HouseInfo2Dto;
import com.ssafy.home.model.dto.HouseInfoDto;
import com.ssafy.home.model.dto.NoticeDto;
import com.ssafy.home.model.dto.SidoGugunCodeDto;
import com.ssafy.home.model.service.HouseService;
import com.ssafy.home.model.service.NoticeService;

@RestController
@RequestMapping("/houseApi")
@CrossOrigin("*")
public class HouseContoller {
	
	private HouseService houseService;
	
	@Autowired
	public HouseContoller(HouseService houseService) {
		this.houseService = houseService;
	}
	
	@PostMapping("/house")
	public ResponseEntity<?> getHouses(@RequestBody HashMap<String, Object> map){
		System.out.println("집 정보 가져오기 실행 !!!!");
		try {
			List<HouseDto> list = houseService.select(map);
			if (list != null && !list.isEmpty()) {
				System.out.println(list.size());
				return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseService.getGugunInSido(sido), HttpStatus.OK);
	}
	

	@GetMapping(value = "/aptlist/{lawd_cd}/{deal_ymd}", produces = "application/json;charset=utf-8")
	public ResponseEntity<String> aptList(@PathVariable("lawd_cd") String lawdCd, @PathVariable("deal_ymd") String dealYmd) throws Exception {
		System.out.println("lawdCd: " + lawdCd);
		System.out.println("deal_ymd: " + dealYmd);
		String serviceKey = "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
		StringBuilder urlBuilder = new StringBuilder(
            "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
                                                                                                                         */
    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
            + "=" + serviceKey);
    urlBuilder
            .append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
            + URLEncoder.encode("15", "UTF-8")); /* 한 페이지 결과 수 */
    urlBuilder.append(
            "&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCd, "UTF-8")); /* 지역코드 */
    urlBuilder.append(
            "&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(dealYmd, "UTF-8")); /* 계약월 */
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    System.out.println("Response code: " + conn.getResponseCode());
    BufferedReader rd;
    if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    } else {
        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
        sb.append(line);
    }
    rd.close();
    conn.disconnect();
    System.out.println(sb.toString());
    JSONObject json = XML.toJSONObject(sb.toString());
    String jsonStr = json.toString();

    return new ResponseEntity<String>(jsonStr, HttpStatus.OK);
}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
	    return new ResponseEntity<List<HouseInfoDto>>(houseService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
	    return new ResponseEntity<List<HouseInfoDto>>(houseService.getAptInDong(dong), HttpStatus.OK);
	}
	
	
	@PostMapping("/house2")
	public ResponseEntity<?> getHouses2(@RequestBody HashMap<String, Object> map){
		System.out.println("요청옴");
		try {
			List<HouseInfo2Dto> list = houseService.select2(map);
			System.out.println("정보 받아옴 ");
			if (list != null && !list.isEmpty()) {
				System.out.println(list.size());
				return new ResponseEntity<List<HouseInfo2Dto>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	

	private ResponseEntity<?> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
