package org.tain.controller.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tain.mybatis.mappers.StudMapper;
import org.tain.utils.IpPrint;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/rest"})
@Slf4j
public class StudRestController {

	@Autowired
	private StudMapper studMapper;
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST}, maxAge = 3600)
	@RequestMapping(value = {"/studs"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<?> test(HttpEntity<String> httpEntity) throws Exception {
		String reqBody = null;
		if (Boolean.TRUE) {
			HttpHeaders reqHeaders = httpEntity.getHeaders();
			reqBody = httpEntity.getBody();
			reqBody = "{\"code\": \"C001\"}";
			log.info(">>>>> ip.info: " + IpPrint.get());
			log.info(">>>>> reqHeaders: " + reqHeaders.toString());
			log.info(">>>>> reqBody: " + reqBody);
		}
		
		List<Map<String,Object>> lst = null;
		if (Boolean.TRUE) {
			Map<String, Object> mapIn = new ObjectMapper().readValue(reqBody, new TypeReference<Map<String, Object>>() {});
			lst = this.studMapper.selectAll(mapIn);
			log.info(">>>>> lst: {}", lst);
		}
		
		MultiValueMap<String,String> headers = null;
		if (Boolean.TRUE) {
			headers = new LinkedMultiValueMap<>();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		}
		return new ResponseEntity<>(lst, headers, HttpStatus.OK);
	}
}
