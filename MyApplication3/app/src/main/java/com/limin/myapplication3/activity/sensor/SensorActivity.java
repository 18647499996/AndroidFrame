package com.limin.myapplication3.activity.sensor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class SensorActivity extends BaseActivity<SensorPresenter> implements SensorContract.View {

    @BindView(R.id.activity_sensor_tv)
    TextView activitySensorTv;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_sensor;
    }


    @Override
    protected TitleBuilder initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected SensorPresenter createPresenter() throws RuntimeException {
        return (SensorPresenter) new SensorPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = Objects.requireNonNull(sensorManager).getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(new SensorEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent event) {
                //values数组中第一个下标的值就是当前的光照强度
                float value = event.values[0];
                activitySensorTv.setText("当前光照强度：" + value + "lx");
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
    public void setPresenter(SensorContract.Presenter presenter) {
        mPresenter = (SensorPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

}
