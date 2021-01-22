package com.limin.myapplication3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

    /**
     * md5字符串
     *
     * @return MD5标记后的字符串
     */
    public static String getMD5(String url) {
        StringBuilder sb;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(url.getBytes());
            sb = new StringBuilder();
            for (byte b : result) {
                String hexString = Integer.toHexString(b & 0xFF);
                if (hexString.length() == 1) {
                    sb.append("0" + hexString);// 0~F
                } else {
                    sb.append(hexString);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
