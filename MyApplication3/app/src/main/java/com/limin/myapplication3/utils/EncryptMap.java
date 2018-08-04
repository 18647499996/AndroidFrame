package com.limin.myapplication3.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class EncryptMap extends HashMap<String,Object> {

    public String encrypt() {
        String s = GsonUtils.toJson(this);

        try {
            String encrypt = Base64Onway.Encrypt(s);
            Log.i("TAG",Base64Onway.Decrypt(encrypt));
            return encrypt;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
