package com.limin.myapplication3.activity.dragtop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;
import github.chenupt.dragtoplayout.DragTopLayout;

/**
 * Description：上拉加载布局
 *
 * @author Created by: Li_Min
 * Time:2018/12/5
 */
public class DragTopLayoutActivity extends BaseActivity implements DragTopLayout.PanelListener {

    @BindView(R.id.activity_dragtop_layout)
    DragTopLayout activityDragtopLayout;

    /**
     * 打开顶部下拉布局
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DragTopLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_dragtoplayout;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("DragTopLayout", R.color.black, R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        immersionBar.transparentStatusBar().statusBarDarkFont(true).init();
        activityDragtopLayout.toggleTopView(false);
    }

    @Override
    protected void addListener() throws RuntimeException {
        activityDragtopLayout.listener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void onPanelStateChanged(DragTopLayout.PanelState panelState) {
        int i = panelState.toInt();
        LogUtils.d("顶部状态：" + i);
    }

    @Override
    public void onSliding(float ratio) {
        LogUtils.d("onSliding：" + ratio);
    }

    @Override
    public void onRefresh() {
        LogUtils.d("onRefresh：");
    }
}
