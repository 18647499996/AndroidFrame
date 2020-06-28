package com.limin.myapplication3.activity.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.login.LoginActivity;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/4
 */
public class WelComeActivity extends BaseActivity {

    @BindView(R.id.activity_welcome_img)
    ImageView activityWelcomeImg;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WelComeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected TitleBuilder initBuilerTitle() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        immersionBar.transparentStatusBar().statusBarDarkFont(false).init();
        activityWelcomeImg.postDelayed(() ->{
            LoginActivity.startActivity(WelComeActivity.this);
            finish();
        },2000);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void onClickDoubleListener(View v) {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    protected BasePresenter createPresenter() throws RuntimeException {
        return null;
    }
}
