package com.limin.myapplication3.activity.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.demo.DemoActivity;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.ToastUtils;

import butterknife.BindView;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class MainActivity extends BaseActivity implements MainConstract.View {

    private MainConstract.Presenter mPresenter;

    @BindView(R.id.activity_tv_content)
    TextView activityTvContent;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter = (MainConstract.Presenter) new MainPresenter(this).Bulider(this);
    }

    @Override
    protected void addListener() {
        activityTvContent.setOnClickListener(this);
    }

    @Override
    protected void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.activity_tv_content:
                mPresenter.login();
                DemoActivity.startActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void showUserModel(UserModel userModel) {

    }

    @Override
    public void setPresenter(MainConstract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(this,msg);
    }
}
