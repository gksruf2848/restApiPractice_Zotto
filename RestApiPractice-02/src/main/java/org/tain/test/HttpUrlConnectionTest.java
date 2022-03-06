package org.tain.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionTest {

	private static final boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		if (flag) test01();
	}
	
	///////////////////////////////////////////////////////////////
	
	private static String url01 = "https://www.naver.com";
	
	private static void test01() throws Exception {
		URL url = new URL(url01);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(">" + line);
		}
		con.disconnect();
		reader.close();
	}
}