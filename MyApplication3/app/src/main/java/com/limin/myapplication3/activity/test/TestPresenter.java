package com.limin.myapplication3.activity.test;

import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.base.BaseView;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/24
 */
class TestPresenter extends BaseSubscription implements TestConstract.Presenter {

    TestPresenter(BaseView view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }
}
