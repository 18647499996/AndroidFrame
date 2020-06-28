package com.limin.myapplication3.base;

import android.content.Context;

import rx.subscriptions.CompositeSubscription;

/**
 * Description：请求取消及Context获取
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public abstract class BaseSubscription<V extends BaseView> implements BasePresenter {

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    private Context context;

    protected V view;

    protected BaseSubscription(V view){
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void onDestroy() {
        if (subscriptions != null) {
            subscriptions.clear();
        }
        subscriptions = null;
    }

    /**
     * 这里的Context根据自己项目需求调用，由于该项目将请求结果回调类（BaseRequestResult）当中统一添加了Loading加载
     * @param context 上下文
     * @return
     */
    public BaseSubscription Bulider(Context context) {
        this.context = context;
        return this;
    }


    public Context getContext() {
        return context;
    }

}
