package com.limin.myapplication3.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.limin.myapplication3.R;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/27
 */
public class LoadingDialogUtils {
    private PopupWindow popupWindow;
    private TextView tipTV;
    private boolean canShow = false;
    private View contentView;
    private Activity activity;

    private static volatile LoadingDialogUtils instance = null;

    private LoadingDialogUtils() {
    }

    public static LoadingDialogUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (LoadingDialogUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new LoadingDialogUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 创建PopupWindow
     */
    public void init(Activity activity, String tip) {
        this.activity = activity;
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        contentView = activity.getLayoutInflater().inflate(R.layout.dialog_loading, new LinearLayout(activity.getApplicationContext()), false);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        tipTV = (TextView) contentView.findViewById(R.id.tipTextView);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        // main.xml加载ImageView
        ImageView spaceshipImage = (ImageView) contentView.findViewById(R.id.img);
        spaceshipImage.setBackgroundResource(R.drawable.progress_round);
        AnimationDrawable animationDrawable = (AnimationDrawable) spaceshipImage.getBackground();
        animationDrawable.start();
        show(tip);
    }


    private void show(final String tip) {
        canShow = true;
        final View parentView = activity.getWindow().getDecorView();
        if (parentView == null) {
            return;
        }
        parentView.post(() -> {
            if (parentView.getWindowToken() == null) {
                return;
            }
            if (TextUtils.isEmpty(tip)) {
                tipTV.setVisibility(View.GONE);
            } else {
                tipTV.setText(tip);
                tipTV.setVisibility(View.VISIBLE);
            }
            if (popupWindow.isShowing()) {
                return;
            }
            if (canShow) {
                popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
            }
        });
    }

    public void dismiss() {
        canShow = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            activity.runOnUiThread(() -> {
                try {
                    if (null != popupWindow) {
                        popupWindow.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            try {
                if (null != popupWindow) {
                    popupWindow.dismiss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 销毁ac引用
     */
    public void destory() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        activity = null;
        contentView = null;
        popupWindow = null;
    }
}
