package com.limin.myapplication3.activity.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.test.TestActivity;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.TitleBuilder;
import com.limin.myapplication3.view.DragView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/19
 */
@SuppressLint("Registered")
public class DemoActivity extends BaseActivity<DemoPresenter> implements DemoContract.View, View.OnTouchListener {

    @BindView(R.id.activity_demo_btn)
    Button activityDemoBtn;
    @BindView(R.id.activity_demo_img_drag)
    ImageView activityDemoImgDrag;
    @BindView(R.id.activity_demo_rel)
    RelativeLayout activityDemoRel;
    @BindView(R.id.activity_demo_tv_drag)
    ImageView activityDemoTvDrag;

    private DemoContract.Presenter mPresenter;
    private int xDelta, yDelta, clickCont;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_demo;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return new TitleBuilder(this)
                .setMiddleTitleBgRes("Demo", R.color.with, R.color.colorAccent)
                .setLeftTextRes("返回", 16, R.color.with)
                .setLeftRelativeLayoutListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        immersionBar.transparentStatusBar().statusBarDarkFont(false).init();
        mPresenter = (DemoContract.Presenter) new DemoPresenter(this).Bulider(this);
        mPresenter.start();
        new DragView.Builder()
                .setActivity(this)
                .setDefaultLeft(30)
                .setDefaultTop(30)
                .setNeedNearEdge(false)
                .setSize(100)
                .setView(activityDemoTvDrag)
                .build();
    }

    @Override
    protected void addListener() {
        activityDemoBtn.setOnClickListener(this::onClickDoubleListener);
        activityDemoImgDrag.setOnTouchListener(this);
    }

    @Override
    protected void onClickDoubleListener(View v) {
        switch (v.getId()) {
            case R.id.activity_demo_btn:
                mPresenter.demo();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroys() {

    }

    @Override
    protected DemoPresenter createPresenter() throws RuntimeException {
        return (DemoPresenter) new DemoPresenter(this).Bulider(this);
    }

    @Override
    public void showUserModel(UserModel userModel) {
        TestActivity.startActivity(this);
    }

    @Override
    public void setPresenter(DemoContract.Presenter presenter) {
        mPresenter = (DemoContract.Presenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(@NotNull String msg) {
        ToastUtils.showShort(msg);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DemoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                clickCont = 1;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
                xDelta = x - params.leftMargin;
                yDelta = y - params.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                clickCont = 2;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                int xDistance = x - xDelta;
                int yDistance = y - yDelta;
                layoutParams.leftMargin = xDistance;
                layoutParams.topMargin = yDistance;
                v.setLayoutParams(layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                if (1 == clickCont) {
                    TestActivity.startActivity(this);
                }
                break;
            default:
                break;
        }
        activityDemoRel.invalidate();
        return true;
    }

}
