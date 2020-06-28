package com.limin.myapplication3.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/24
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestConstract.View {

    private TestConstract.Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
        mPresenter.start();
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
    protected TestPresenter createPresenter() throws RuntimeException {
        return (TestPresenter) new TestPresenter(this).Bulider(this);
    }

    @Override
    protected TitleBuilder initBuilerTitle() {
        return new TitleBuilder(this)
                .setMiddleTitleBgRes("Test",R.color.black,R.color.with);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setPresenter(TestConstract.Presenter presenter) {
        mPresenter = (TestConstract.Presenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
