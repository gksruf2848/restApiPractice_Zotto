package org.tain.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpConnection {

	private static final int CONN_TIMEOUT = 30;
	private static final int READ_TIMEOUT = 10;
	//private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7";
	private static final String USER_AGENT = "Mozilla/5.0";
	//user agent란, HTTP 요청을 보내는 디바이스와 브라우저 등 사용자 소프트웨어의 식별 정보를 담고 있는 request header의 한 종류이다.
	//임의로 수정될 수 없는 값이고, 보통 HTTP 요청 에러가 발생했을 때 요청을 보낸 사용자 환경을 알아보기 위해 사용한다.
	
	private static boolean flag = true;
	
	public static String getResponse(final Map<String,Object> root) {
		String strUrl = (String)root.get("url");
		String method = (String)root.getOrDefault("method","get");
		method = method.toUpperCase();
		if (flag) System.out.printf("—> strUrl: %s, method: %s\n", strUrl, method);
		
		HttpURLConnection con = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(method);
			
			con.setDefaultUseCaches(true);
			con.setConnectTimeout(CONN_TIMEOUT * 1000);
			con.setReadTimeout(READ_TIMEOUT * 1000);
			
			if (true) {
				@SuppressWarnings("unchecked")
				Map<String,Object> properties = (Map<String,Object>) root.get("properties");
				if (properties != null) {
					for (Map.Entry<String, Object> entry : properties.entrySet()) {
						con.setRequestProperty(entry.getKey(), (String) entry.getValue());
					}
					System.out.println("—> properties: " + properties);
				}
			}
			
			if (flag) {
				// authorization
				String auth = "username:password";
				String encodedBytes = Base64.getEncoder().encodeToString(auth.getBytes());
				String authorization = "Basic " + encodedBytes;
				con.setRequestProperty("Authorization", authorization);
				System.out.println("—> authorization: " + authorization);
			}
			
			String request = "{}";
			if (flag) {
				@SuppressWarnings("unchecked")
				Map<String,Object> mapReq = (Map<String,Object>) root.get("request");
				if (mapReq != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					request = objectMapper.writeValueAsString(mapReq);
					request = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapReq);
					System.out.println("—> request: " + request);
				}
				con.setRequestProperty("Content-Length", String.valueOf(request.length()));
			}
			
			if (flag) {
				// send req
				con.setDoOutput(true);
				//con.getOutputStream().write(strRequest.getBytes("utf-8")); // POST 호출
				DataOutputStream dos = new DataOutputStream(con.getOutputStream());
				dos.writeBytes(request);
				dos.flush();
				dos.close();
			}
			
			if (flag) {
				int resCode = con.getResponseCode();
				System.out.println("—> resCode: " + resCode);
			}
			
			if (flag) {
				// recv res
				//con.setDoInput(true);
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line).append('\n');
				}
				br.close();
			}
			
			if (flag) {
				System.out.println("—> header: " + con.getHeaderFields());
				//System.out.println("—> properties: " + con.getRequestProperties());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
				if (flag) System.out.println("—> disconnect()");
			}
		}
		
		return sb.toString();
	}
	
	/////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> root = null;
		if (flag) {
			root = new HashMap<>();
			root.put("url", "http://localhost:8080/v0.1/rest/lesns");
			root.put("method", "get");
			
			if (flag) {
				Map<String, Object> header = new HashMap<>();
				root.put("header", header);
			}
			if (flag) {
				Map<String, Object> properties = new HashMap<>();
				properties.put("User-Agent", USER_AGENT);
				properties.put("Connection", "keep-alive"); 
				properties.put("Content-Type", "application/x-www-form-urlencoded");
				properties.put("Content-Type", "application/json; charset=utf-8");
				properties.put("Content-Type", "application/json; utf-8");
				properties.put("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4"); 
				properties.put("Accept-Encoding", "gzip,deflate,sdch"); 
				properties.put("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
				properties.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				properties.put("Accept", "application/json");
				properties.put("Cookie", "1234567890");
				root.put("properties", properties);
			}
			if (flag) {
				Map<String, Object> request = new LinkedHashMap<>();
				request.put("code", "C001");
				request.put("dummy", "dummyValue");
				root.put("request", request);
			}
		}
		
		String strRes = HttpConnection.getResponse(root);
		if (flag) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode objJson = objectMapper.readTree(strRes);
			System.out.println("—> strRes1: " + objectMapper.writeValueAsString(objJson));
			System.out.println("—> strRes2: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objJson));
		}
	}
}