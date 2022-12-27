package com.limin.myapplication3.refresh.listener;


import androidx.annotation.NonNull;

import com.limin.myapplication3.refresh.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by SCWANG on 2017/5/26.
 */

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
