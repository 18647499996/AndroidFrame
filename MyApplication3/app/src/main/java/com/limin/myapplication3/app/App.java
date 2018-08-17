package com.limin.myapplication3.app;

import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.Constant;

import java.util.Optional;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 常用工具类Utils 详见Api https://blog.csdn.net/qq_33445600/article/details/78487857
        Utils.init(this);
        CrashUtils.init();
        UserModel userModel = new UserModel();
//        Optional.of(userModel).map(UserModel::getNickname).orElse("空指针异常");
    }
}
