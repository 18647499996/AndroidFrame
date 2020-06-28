package com.limin.myapplication3.activity.zoomscrollview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.VitaeModel;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.List;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/12/5
 */
public class ZoomScrollViewActivity extends BaseActivity<ZoomScrollViewPresenter> implements ZoomScrollViewConstract.View {

    @BindView(R.id.activity_vitae_img_portrait)
    ImageView activityVitaeImgPortrait;
    @BindView(R.id.activity_vitae_tv_name)
    TextView activityVitaeTvName;
    @BindView(R.id.activity_vitae_tv_kpi)
    TextView activityVitaeTvKpi;
    @BindView(R.id.activity_vitae_tv_title)
    TextView activityVitaeTvTitle;
    @BindView(R.id.activity_vitae_rel_back)
    RelativeLayout activityVitaeRelBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.activity_vitae_rx)
    RecyclerView activityVitaeRx;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    private ZoomScrollViewConstract.Presenter presenter;
    private ZoomScrollViewAdapter zoomScrollViewAdapter;

    /**
     * 开启图片放大页面
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ZoomScrollViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_zoomscrollview;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        activityVitaeTvTitle.setText("小巴扎华茂店");
        presenter = new ZoomScrollViewPresenter(this);
        zoomScrollViewAdapter = new ZoomScrollViewAdapter(R.layout.item_vitae,this);
        activityVitaeRx.setLayoutManager(new LinearLayoutManager(this));
        activityVitaeRx.setAdapter(zoomScrollViewAdapter);
        presenter.start();
        presenter.queryData();
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    protected ZoomScrollViewPresenter createPresenter() throws RuntimeException {
        return (ZoomScrollViewPresenter) new ZoomScrollViewPresenter(this).Bulider(this);
    }

    @Override
    public void setPresenter(ZoomScrollViewConstract.Presenter presenter) {
        this.presenter = (ZoomScrollViewConstract.Presenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showListData(List<VitaeModel> vitaeModelList) {
        zoomScrollViewAdapter.setNewData(vitaeModelList);
    }
}
