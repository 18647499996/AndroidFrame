package com.limin.myapplication3.base;

import android.content.Context;

import rx.subscriptions.CompositeSubscription;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public abstract class BaseSubscription<T> implements BasePresenter {

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    private Context context;

    @Override
    public void onDestroy() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
        subscriptions = null;
    }

    public BaseSubscription<T> Bulider(Context context) {
        this.context = context;
        return this;
    }


    public Context getContext() {
        return context;
    }
}
