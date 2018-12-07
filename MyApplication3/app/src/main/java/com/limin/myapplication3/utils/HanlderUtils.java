package com.limin.myapplication3.utils;

import android.os.Handler;

/**
 * ============================================================
 * <p/>
 * 版 权 ： 诺易(北京)科技服务有限公司
 * <p/>
 * 作 者 ： Li_Min
 * <p/>
 * 版 本 ： 2.0
 * <p/>
 * 创建日期 ：2016/12/14
 * <p/>
 * 描 述 ：Hnalder工具类
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class HanlderUtils {

    public Handler handler = new Handler();
    public Runnable runnable = null;
    public static final long MILLIS = 1000;



    private static class HanlderUtilsHolder {
        private static final HanlderUtils INSTANCE = new HanlderUtils();
    }
    public HanlderUtils(){}
    public static final HanlderUtils getInstance() {
        return HanlderUtilsHolder.INSTANCE;
    }

    public void delayExecute(Runnable runnable, long millis){
        this.runnable = runnable;
        handler.postDelayed(runnable,millis);
    }
    public void clearHanlder() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
