package com.limin.myapplication3.app;

import android.app.Application;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.limin.myapplication3.BuildConfig;
import com.limin.myapplication3.refresh.SmartRefreshLayout;

import cn.wildfirechat.push.PushService;

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
        LogUtils.getConfig().
                setLogSwitch(BuildConfig.DEBUG).
                setBorderSwitch(true);
        CrashUtils.init();
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            //全局设置（优先级最低）
            layout.setEnableLoadMore(true);
            layout.setEnableAutoLoadMore(true);
            layout.setEnableOverScrollDrag(true);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableScrollContentWhenRefreshed(true);
            layout.setEnableFooterFollowWhenLoadFinished(true);
        });
        PushService.init(this,BuildConfig.APPLICATION_ID);
    }
}
