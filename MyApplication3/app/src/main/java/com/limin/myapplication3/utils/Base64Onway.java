package com.limin.myapplication3.utils;


import java.io.UnsupportedEncodingException;


/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class Base64Onway {

	/**
	 * 加密
	 */
	public static String Encrypt(String plainText) throws UnsupportedEncodingException {
		return Base64.getEncoder().encodeToString(plainText.getBytes("UTF-8"));
	}

	/**
	 * 解密
	 */
	public static String Decrypt(String encryptData) throws UnsupportedEncodingException {
		return new String(Base64.getDecoder().decode(encryptData), "UTF-8");
	}


}
