package com.limin.myapplication3.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.demo.DemoActivity;
import com.limin.myapplication3.adapter.TabAdapter;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.AppShortCutUtil;
import com.limin.myapplication3.utils.ListDataUtils;
import com.limin.myapplication3.utils.TitleBuilder;
import com.limin.myapplication3.utils.UserManagerUtils;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Description：主界面
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class MainActivity extends BaseActivity implements MainConstract.View, ViewPager.OnPageChangeListener {


    @BindView(R.id.activity_main_vp)
    ViewPager activityMainVp;
    @BindView(R.id.activity_main_tab)
    TabLayout activityMainTab;

    private MainConstract.Presenter mPresenter;
    private List<Fragment> mFragments = ListDataUtils.mainFragment();
    private String[] titleArray = ListDataUtils.titleArray();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected TitleBuilder initBuilerTitle() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
        immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
        mPresenter = (MainConstract.Presenter) new MainPresenter(this).Bulider(this);
        mPresenter.start();
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), mFragments, titleArray);
        activityMainVp.setAdapter(tabAdapter);
        activityMainTab.setupWithViewPager(activityMainVp);
        activityMainVp.setOffscreenPageLimit(mFragments.size());
        for (int i = 0; i < titleArray.length; i++) {
            Objects.requireNonNull(activityMainTab.getTabAt(i)).setText(titleArray[i]);
        }
        LogUtils.d("首页获取token：" + UserManagerUtils.getInstance().getToken());
        AppShortCutUtil.addNumShortCut(this,MainActivity.class,true,"30",false);
    }

    @Override
    protected void addListener() {
        activityMainVp.addOnPageChangeListener(this);
    }

    @Override
    protected void onClickDoubleListener(View v) {
    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }


    @Override
    public void showUserModel(UserModel userModel) {
        DemoActivity.startActivity(this);
    }

    @Override
    public void showOutLogin() {
        // 用户登出以后需要处理的逻辑（数据库及缓存文件清空）                                              `
    }

    @Override
    public void setPresenter(MainConstract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
                break;
            case 1:
                immersionBar.transparentStatusBar().statusBarDarkFont(false).init();
                break;
            case 2:
                immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
                break;
            case 3:
                immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
