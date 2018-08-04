package com.limin.myapplication3.base;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseException {

    public static ApiException throwableUtils(Throwable throwable) {
        ApiException apiException;
        if (throwable instanceof HttpException || throwable instanceof ConnectException) {
            apiException = new ApiException(throwable, ERROR.HTTP_ERROR);
            apiException.setDisplayMessage(ERROR.STR_HTTP_ERROR);
            Log.d("BaseException", "网络异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof BaseTransformer.ServerException) {
            BaseTransformer.ServerException resultException = (BaseTransformer.ServerException) throwable;
            apiException = new ApiException(resultException, resultException.getCode());
            apiException.setDisplayMessage(resultException.getMsg());
            Log.d("BaseException", "服务器异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof JsonParseException || throwable instanceof JSONException || throwable instanceof ParseException) {
            apiException = new ApiException(throwable, ERROR.PARSE_ERROR);
            apiException.setDisplayMessage(ERROR.STR_PARSE_ERROR);
            Log.d("BaseException", "解析异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
            //环信无网络判断
        } else if (throwable instanceof SocketException ||
                throwable instanceof UnknownHostException ||
                throwable instanceof ApiException) {
            apiException = new ApiException(throwable, ERROR.NO_NET_ERROR);
            apiException.setDisplayMessage(ERROR.STR_NO_NET_ERROR);
            Log.d("BaseException", "连接异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof SocketTimeoutException) {
            apiException = new ApiException(throwable, ERROR.TIME_OUT_ERROR);
            apiException.setDisplayMessage(ERROR.STR_TIME_OUT_ERROR);
            Log.d("TAG", "超时异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else if (throwable instanceof InterruptedException) {
            apiException = new ApiException(throwable, ERROR.REQUEST_INTERRUPTED_ERROR);
            apiException.setDisplayMessage(ERROR.STR_REQUEST_INTERRUPTED_ERROR);
            Log.d("BaseException", "中断异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        } else {
            apiException = new ApiException(throwable, ERROR.UNKNOWN);
            apiException.setDisplayMessage(ERROR.STR_UNKNOWN);
            Log.d("BaseException", "其他异常：" + apiException.getDisplayMessage() + "\n异常编码：" + apiException.getCode() + "\n异常信息：" + apiException.getMessage());
            return apiException;
        }

    }

    /**
     * 异常抓捕器
     */
    public static class ApiException extends Exception {

        private final Throwable throwable;
        private int code;
        private String displayMessage;

        ApiException(Throwable throwable, int code, String displayMessage) {
            this.throwable = throwable;
            this.code = code;
            this.displayMessage = displayMessage;
        }

        public ApiException(Throwable throwable, int errorCode) {
            this.throwable = throwable;
            this.code = errorCode;
        }


        public Throwable getThrowable() {
            return throwable;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDisplayMessage() {
            return displayMessage;
        }

        public void setDisplayMessage(String displayMessage) {
            this.displayMessage = displayMessage;
        }
    }

    /**
     * 异常协议信息
     */
    public class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 无网络
         */
        public static final int NO_NET_ERROR = 1004;
        /**
         * 请求超时
         */
        public static final int TIME_OUT_ERROR = 1005;
        /**
         * 请求取消
         */
        public static final int REQUEST_INTERRUPTED_ERROR = 1006;
        /**
         * TOKEN异常
         */
        public static final int REQUEST_TOKEN_ERROR = 1008;
        /**
         * 环信登录
         */
        public static final int REQUEST_EASY_LOGIN_ERROR = 1009;

        /**
         * 连接失败
         */
        public static final int NETWORD_ERROR = 1007;

        public static final String STR_HTTP_ERROR = "服务器连接失败，请稍后连接";

        public static final String STR_PARSE_ERROR = "服务器连接异常，请稍后连接";

        public static final String STR_NO_NET_ERROR = "无网络连接，请检查您的网络";

        public static final String STR_REQUEST_INTERRUPTED_ERROR = "服务器连接中断，请稍后连接";

        public static final String STR_TIME_OUT_ERROR = "服务器连接超时，请稍后连接";

        public static final String STR_REQUEST_TOKEN_ERROR = "请登录";

        public static final String STR_UNKNOWN = "未知错误";

    }
}
