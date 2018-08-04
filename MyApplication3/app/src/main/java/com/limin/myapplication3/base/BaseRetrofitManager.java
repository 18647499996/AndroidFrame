package com.limin.myapplication3.base;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseRetrofitManager {

    private BaseService baseService;

    private static volatile BaseRetrofitManager instance = null;

    private BaseRetrofitManager() {
    }

    public static BaseRetrofitManager getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (BaseRetrofitManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new BaseRetrofitManager();
                }
            }
        }
        return instance;
    }


    public BaseService baseService() {
        initRetrofit();
        return baseService;
    }

    /**
     * 初始化Retrofit管理器配置
     */
    private void initRetrofit() {
        // 初始化OkHttp配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new LogInterceptor())
                .build();
        // 初始化Retrofit配置
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseHttpUrl.BASICS_SERVICE)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        baseService = retrofit.create(BaseService.class);
    }

    /**
     * Log日志拦截器
     */
    private static class LogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            String bodyStr = bodyToString(request);
            Log.i("TAG", String.format("请求参数 %s: body=   %s", request.url(), bodyStr));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            if (response.body() != null) {
                ResponseBody body = response.peekBody(1024 * 1024);
                Log.i("TAG", String.format(Locale.getDefault(), "返回数据 %s in %.1fms%n   %s", response.request().url(), (t2 - t1) / 1e6d, body.string()));
            } else {
                Log.i("TAG", "body null");

            }
            return response;

        }

        private static String bodyToString(final Request request) {

            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                if (copy == null || copy.body() == null) {
                    return "";
                }
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            }
        }
    }
}
