package com.limin.myapplication3.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.utils.HanlderUtils;

/**
 * Description：推送服务
 *
 * @author Created by: Li_Min
 * Time:2018/11/11
 */
public class BackgroundService extends Service {

    public static void startService(Context context) {
        Intent intent = new Intent(context,BackgroundService.class);
        context.startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        init();
    }

    private void init() {
        HanlderUtils.getInstance().delayExecute(() -> {
            init();
            LogUtils.d("开启服务");
        },HanlderUtils.MILLIS);
    }

    public static void stopService(Context context){
        Intent intent = new Intent(context,BackgroundService.class);
        context.stopService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HanlderUtils.getInstance().clearHanlder();
        LogUtils.d("停止服务--------------------");
    }
}
