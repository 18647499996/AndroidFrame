package com.limin.myapplication3.activity.sensor;


import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class SensorPresenter extends BaseSubscription<SensorContract.View> implements SensorContract.Presenter {


    SensorPresenter(SensorContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}