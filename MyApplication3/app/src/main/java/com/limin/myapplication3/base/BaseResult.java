package com.limin.myapplication3.base;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Description：服务器返回结果定义协议
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseResult<T> implements Serializable {
    @SerializedName(value = "code", alternate = {"status","httpCode"})
    private int code;
    @SerializedName(value = "msg",alternate = {"message"})
    private String msg;
    @SerializedName(value = "data",alternate = {"result"})
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
