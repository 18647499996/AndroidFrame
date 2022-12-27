package com.limin.myapplication3.refresh.api;

import androidx.annotation.NonNull;

/**
 * 二级刷新监听器
 */
public interface OnTwoLevelListener {
    /**
     * 二级刷新触发
     * @param refreshLayout 刷新布局
     * @return true 将会展开二楼状态 false 关闭刷新
     */
    boolean onTwoLevel(@NonNull RefreshLayout refreshLayout);
}