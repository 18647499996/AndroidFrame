package com.limin.myapplication3.utils.rx;

import rx.Subscriber;

public abstract class EventSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {
        onEvent(t);
    }

    public abstract void onEvent(T t);
}
