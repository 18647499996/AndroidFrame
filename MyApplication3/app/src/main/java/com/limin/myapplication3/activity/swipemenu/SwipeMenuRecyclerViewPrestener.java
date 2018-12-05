package com.limin.myapplication3.activity.swipemenu;

import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/20
 */
public class SwipeMenuRecyclerViewPrestener extends BaseSubscription implements SwipeMenuRecyclerViewConstract.Presenter {

    private SwipeMenuRecyclerViewConstract.View view;

    SwipeMenuRecyclerViewPrestener(SwipeMenuRecyclerViewConstract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
