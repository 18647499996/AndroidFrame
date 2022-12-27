package com.limin.myapplication3.refresh.listener;


import androidx.annotation.NonNull;

import com.limin.myapplication3.refresh.api.RefreshLayout;

/**
 * 加载更多监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
