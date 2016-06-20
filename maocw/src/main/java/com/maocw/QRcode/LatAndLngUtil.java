package com.maocw.QRcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 输入地址获取经纬度
 * @author mao
 *
 */
public final class LatAndLngUtil {
	
	public static Object[] getCoordinate(String addr) throws IOException {
		String lng = null;//经度
		String lat = null;//纬度
		String address = null; 
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); 
		}
		
		String key = "f247cdb592eb43ebac6ccd27f796e2d2"; 
		String url = String .format("http://api.map.baidu.com/geocoder?address=%s&output=json&key=%s", address, key); 
		URL myURL = null; 
		URLConnection httpsConn = null; 
		try {
			myURL = new URL(url); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理 
			if (httpsConn != null) { 
			insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8"); 
			br = new BufferedReader(insr); 
			String data = null; 
			int count = 1;
			while ((data= br.readLine())!=null) {
				if (count==5) {
					lng = (String)data.subSequence(data.indexOf(":")+1, data.indexOf(","));//经度
					count++;
				}else if (count==6) {
					lat = data.substring(data.indexOf(":")+1);//纬度
					count++;
				}else {
					count++;
				}
			}
			}
		}catch (IOException  e) {
			e.printStackTrace();
		}finally {
			if (insr!=null) {
				insr.close();
			}
			if (br!=null) {
				br.close();
			}
		}
		return new Object[]{lng,lat}; 
	}
}
