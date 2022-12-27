package com.limin.myapplication3.refresh.api;

import android.content.Context;
import androidx.annotation.NonNull;

/**
 * 默认全局初始化器
 * Created by Administrator on 2018/5/29 0029.
 */

public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout layout);
}
