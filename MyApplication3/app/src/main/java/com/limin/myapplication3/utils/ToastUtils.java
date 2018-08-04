package com.limin.myapplication3.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 短时间显示Toast
     * @param context 上下文
     * @param message 显示的文本
     */
    public static void showShort(Context context, CharSequence message) {
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     * @param context 上下文
     * @param message 显示的文本id
     */
    public static void showShort(Context context, String message) {
        if (context == null) {
            return;
        }
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity ) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     * @param context 上下文
     * @param message 显示的文本
     */
    public static void showLong(Context context, CharSequence message) {
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity ) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     * @param context 上下文
     * @param message 显示的文本id
     */
    public static void showLong(Context context, int message) {
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity ) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     * @param context 上下文
     * @param message 显示的文本
     * @param duration 持续的时间
     */
    public static void show(Context context, CharSequence message, int duration) {
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity ) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     * @param context 上下文
     * @param message 显示的文本id
     * @param duration 持续时间
     */
    public static void show(Context context, int message, int duration) {
        //防止传入的context是activity ，容易引发内存泄露
        if(context instanceof Activity ) {
            context = context.getApplicationContext();
        }
        if (null == toast) {
            toast = Toast.makeText(context, message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 隐藏toast
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
