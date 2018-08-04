package com.limin.myapplication3.app;

import android.app.Application;

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
    }
}
