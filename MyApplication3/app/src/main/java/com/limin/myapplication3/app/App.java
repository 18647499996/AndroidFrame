package com.limin.myapplication3.app;

import android.app.Application;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.Utils;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class App extends Application {

    private static App instance;


    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 常用工具类Utils 详见Api https://blog.csdn.net/qq_33445600/article/details/78487857
        Utils.init(this);
        CrashUtils.init();
    }
}
