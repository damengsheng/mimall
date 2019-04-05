package com.qianfeng.xiaomi.utils;

import java.util.Base64;

//base64
public class Base64Utils {
	//base64编码
	public static String encode(String msg){
		return Base64.getEncoder().encodeToString(msg.getBytes());
	}
	//base64解码
	public static String decode(String msg){
		return new String(Base64.getDecoder().decode(msg));
	}
}
