package com.limin.myapplication3.refresh.listener;


import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.limin.myapplication3.refresh.api.RefreshLayout;
import com.limin.myapplication3.refresh.constant.RefreshState;

import static androidx.annotation.RestrictTo.Scope.LIBRARY;
import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static androidx.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * 刷新状态改变监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnStateChangedListener {
    /**
     * 状态改变事件 {@link RefreshState}
     * @param refreshLayout RefreshLayout
     * @param oldState 改变之前的状态
     * @param newState 改变之后的状态
     */
    @RestrictTo({LIBRARY,LIBRARY_GROUP,SUBCLASSES})
    void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState);
}
