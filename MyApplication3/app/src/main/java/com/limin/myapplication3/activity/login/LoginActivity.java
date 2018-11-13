package com.limin.myapplication3.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.main.MainActivity;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.UserInfoModel;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/24
 */
public class LoginActivity extends BaseActivity implements LoginConstract.View, TextView.OnEditorActionListener {

    @BindView(R.id.activity_login_img_bg)
    ImageView activityLoginImgBg;
    @BindView(R.id.activity_login_tv_title)
    TextView activityLoginTvTitle;
    @BindView(R.id.activity_login_edt_user)
    EditText activityLoginEdtUser;
    @BindView(R.id.activity_login_edt_pws)
    EditText activityLoginEdtPws;

    private LoginConstract.Presenter presenter;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected TitleBuilder initBuilerTitle() {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        immersionBar.transparentStatusBar().statusBarDarkFont(false).init();
        presenter = (LoginConstract.Presenter) new LoginPresenter(this).Bulider(this);
        presenter.start();
        presenter.startAnimation(activityLoginImgBg);
        activityLoginEdtUser.setText("18647499996");
        activityLoginEdtPws.setText("123456");
    }

    @Override
    protected void addListener() {
        activityLoginEdtPws.setOnEditorActionListener(this);
    }

    @Override
    protected void onClickDoubleListener(View v) {

    }

    @Override
    protected void onDestroys() {
        presenter.onDestroy();
    }

    @Override
    public void setPresenter(LoginConstract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEND:
                presenter.login(activityLoginEdtUser.getText().toString().trim(),activityLoginEdtPws.getText().toString().trim());
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void showUserModel(UserInfoModel userModel) {
        MainActivity.startActivity(this);
        finish();
    }

}
