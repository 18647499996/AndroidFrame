package com.limin.myapplication3.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import retrofit2.http.PUT;

/**
 * Description：服务器接口定义地址
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseHttpUrl {

    public static final int MAIN_TYPE = 1;

    public static final int COMMUNITY_TYPE = 2;

    public static final int JOURNALISM_TYPE = 3;

    /**
     * 用户服务器地址
     */
    public static final String USER_SERVICE = "http://pre-gds.27aichi.cn/passport/";

    /**
     * 社区服务器地址
     */
    public static final String COMMUNITY_SERVICE = "http://pre-newsns.27aichi.cn/";

    public static final String JOURNALISM_SERVICE = "http://highlights.youth.cn/";



    @IntDef({MAIN_TYPE,COMMUNITY_TYPE,JOURNALISM_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @interface isChekout{

    }
}
