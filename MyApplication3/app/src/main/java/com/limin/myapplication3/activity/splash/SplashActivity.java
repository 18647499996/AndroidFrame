package com.limin.myapplication3.activity.splash;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.limin.myapplication3.activity.welcome.WelComeActivity;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/4
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WelComeActivity.startActivity(this);
        finish();
    }
}
