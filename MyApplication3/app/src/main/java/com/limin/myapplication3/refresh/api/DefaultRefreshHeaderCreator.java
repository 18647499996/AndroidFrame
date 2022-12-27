package com.limin.myapplication3.refresh.api;

import android.content.Context;
import androidx.annotation.NonNull;

/**
 * 默认Header创建器
 * Created by SCWANG on 2018/1/26.
 */
public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout);
}
