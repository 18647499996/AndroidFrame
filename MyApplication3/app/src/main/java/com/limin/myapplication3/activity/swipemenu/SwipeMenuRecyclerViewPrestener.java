package com.limin.myapplication3.activity.swipemenu;

import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/20
 */
public class SwipeMenuRecyclerViewPrestener extends BaseSubscription<SwipeMenuRecyclerViewConstract.View> implements SwipeMenuRecyclerViewConstract.Presenter {


    SwipeMenuRecyclerViewPrestener(SwipeMenuRecyclerViewConstract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onDestroy() {

    }
}
