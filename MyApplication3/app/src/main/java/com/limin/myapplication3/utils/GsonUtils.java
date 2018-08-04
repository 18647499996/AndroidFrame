package com.limin.myapplication3.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class GsonUtils {
    static {
        mGson = new GsonBuilder().disableHtmlEscaping().create();
    }

    private static Gson mGson;

    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return mGson.toJson(object);

    }



    public static  <T> T fromJson(String json, Class<T> t) throws RuntimeException {
        T t1=null;
        try {
             t1 = mGson.fromJson(json, t);

        }
        catch (RuntimeException e){

            e.printStackTrace();
//            throw new JsonSyntaxFailException(" JsonSyntax fail: "+json,e);
            return null;
        }
        return t1 ;

    }

    public static boolean isJsonArray(String json) {

        return json.startsWith("[");
    }

    public static <T> List<T> jsonArrayList(String launch, Class<T> tClass) {
        List<T> tList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(launch);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t = new Gson().fromJson(jsonObject.toString(),tClass);
                tList.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tList;
    }
}
