package com.limin.myapplication3.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/12/21
 */
public class ActivityA extends BaseActivity {


    @BindView(R.id.activity_a_tv)
    TextView activityATv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ActivityA.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        LogUtils.d("------------onCreate--------------AAAAAAAAAAAA");
    }

    @Override
    protected void addListener() throws RuntimeException {
        activityATv.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {
        switch (view.getId()) {
            case R.id.activity_a_tv:
                activityATv.setText("ActivityA");
                ActivityB.startActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {
        LogUtils.d("------------onDestroys--------------AAAAAAAAAAAA");
    }

    @Override
    protected BasePresenter createPresenter() throws RuntimeException {
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("----------------AAAAAAAAAAAAAAA-------------------");
        activityATv.setText("我是从AvtivityB跳转过来的");
    }
}
