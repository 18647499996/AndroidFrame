package com.limin.myapplication3.activity.bluetooth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/20
 */
public class BluetoothActivity extends BaseActivity<BluetoothPresenter> implements BluetoothConstract.View {


    /**
     * 启动文件管理器
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BluetoothActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_bluetooth;
    }

    @Override
    protected TitleBuilder initBuilderTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("蓝牙通讯");
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.onSubscribe();
        mPresenter.getBluetoothDeviceList(this);
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
    protected BluetoothPresenter createPresenter() throws RuntimeException {
        return (BluetoothPresenter) new BluetoothPresenter(this).builder(this);
    }

    @Override
    public void setPresenter(BluetoothConstract.Presenter presenter) {
        mPresenter = (BluetoothPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
