package com.limin.myapplication3.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Description：服务器接口定义地址
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public class BaseHttpUrl {

    public static final int MAIN_SERVICE = 1;

    public static final String BASICS_SERVICE = "http://dev.gds.27aichi.cn/";



    @IntDef({MAIN_SERVICE})
    @Retention(RetentionPolicy.SOURCE)
    @interface isChekout{

    }
}
