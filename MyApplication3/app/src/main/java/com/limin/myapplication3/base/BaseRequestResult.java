package com.limin.myapplication3.base;

import android.content.Context;

import com.limin.myapplication3.utils.WeiboDialogUtils;

import rx.Subscriber;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public abstract class BaseRequestResult<T> extends Subscriber<T>  {

    private static final int ERROR = 10000;
    private Context context;

    protected BaseRequestResult(Context context) {
        this.context = context;
    }


    @Override
    public void onStart() {
        super.onStart();
        WeiboDialogUtils.getInstance().createLoadingDialog(context,"");
    }

    @Override
    public void onCompleted() {
        onCompletedListener();
    }

    @Override
    public void onError(Throwable e) {
        WeiboDialogUtils.getInstance().closeDialog();
        if (e instanceof BaseException.ApiException) {
            onErrorListener((BaseException.ApiException) e);
        }else{
            onErrorListener(new BaseException.ApiException(e,ERROR,"未知错误"));
        }
    }

    @Override
    public void onNext(T t) {
        WeiboDialogUtils.getInstance().closeDialog();
        onNextListener(t);
    }

    protected abstract void onCompletedListener();

    protected abstract void onErrorListener(BaseException.ApiException e);

    protected abstract void onNextListener(T t);

}
