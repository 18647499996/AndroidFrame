package com.limin.myapplication3.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.demo.DemoActivity;
import com.limin.myapplication3.activity.test.TestActivity;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;

/**
 * Description：主界面
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class MainActivity extends BaseActivity implements MainConstract.View {

    @BindView(R.id.user)
    Button user;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.activity_tv_content)
    TextView activityTvContent;

    private MainConstract.Presenter mPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected TitleBuilder initBuilerTitle() {
        return new TitleBuilder(this)
                .setMiddleTitleBgRes("首页")
                .setLeftTextRes("返回")
                .setLeftRelativeLayoutListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setSwipeBackEnable(false);
        mPresenter = (MainConstract.Presenter) new MainPresenter(this).Bulider(this);
        mPresenter.start();
    }

    @Override
    protected void addListener() {
        activityTvContent.setOnClickListener(this::onClickDoubleListener);
        user.setOnClickListener(this::onClickDoubleListener);
        login.setOnClickListener(this::onClickDoubleListener);
    }

    @Override
    protected void onClickDoubleListener(View v) {
        switch (v.getId()) {
            case R.id.activity_tv_content:
                mPresenter.login();
                break;
            case R.id.user:
                TestActivity.startActivity(this);
                break;
            case R.id.login:

                break;
            default:
                break;
        }
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
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
