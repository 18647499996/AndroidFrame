package com.limin.myapplication3.base;

import android.content.Context;

import com.limin.myapplication3.utils.WeiboDialogUtils;

import rx.Subscriber;

/**
 * Description：网络请求返回结果回调
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public abstract class BaseRequestResult<T> extends Subscriber<T>  {

    private static final int ERROR = 10000;
    private Context context;

    protected BaseRequestResult(){

    }

    protected BaseRequestResult(Context context) {
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != context) {
            WeiboDialogUtils.getInstance().createLoadingDialog(context, "");
        }
    }

    @Override
    public void onCompleted() {
        onCompletedListener();
    }

    @Override
    public void onError(Throwable e) {
        if (null != context) {
            WeiboDialogUtils.getInstance().closeDialog();
        }
        if (e instanceof BaseException.ApiException) {
            onErrorListener((BaseException.ApiException) e);
        }else{
            onErrorListener(new BaseException.ApiException(e,ERROR,"未知错误"));
        }
    }

    @Override
    public void onNext(T t) {
        if (null != context) {
            WeiboDialogUtils.getInstance().closeDialog();
        }
        onNextListener(t);
    }

    /**
     * 请求完成回调
     */
    protected abstract void onCompletedListener();

    /**
     * 请求异常回调
     * @param e 异常信息
     */
    protected abstract void onErrorListener(BaseException.ApiException e);

    /**
     * 请求成功回调
     * @param t 返回泛型Model
     */
    protected abstract void onNextListener(T t);

}
