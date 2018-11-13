package com.limin.myapplication3.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.refresh.api.RefreshHeader;
import com.limin.myapplication3.refresh.api.RefreshKernel;
import com.limin.myapplication3.refresh.api.RefreshLayout;
import com.limin.myapplication3.refresh.constant.RefreshState;
import com.limin.myapplication3.refresh.constant.SpinnerStyle;
import com.limin.myapplication3.refresh.internal.ArrowDrawable;
import com.limin.myapplication3.refresh.internal.ProgressDrawable;
import com.limin.myapplication3.refresh.util.DensityUtil;

/**
 * Description：自定义刷新头部
 *
 * @author Created by: Li_Min
 * Time:2018/9/4
 */
public class ClassicsHeader extends LinearLayout implements RefreshHeader {

    /**
     * 下拉箭头
     */
    private ImageView mArrowView;
    /**
     * 刷新动画视图
     */
    private ImageView mProgressView;
    /**
     * 停止加载显示
     */
    private ImageView mStopView;
    /**
     * 刷新动画
     */
    private AnimationDrawable animationDrawable;

    public ClassicsHeader(Context context) {
        this(context, null);
    }
    public ClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        setGravity(Gravity.CENTER);
//        mProgressDrawable = new ProgressDrawable();
        mArrowView = new ImageView(context);
        mProgressView = new ImageView(context);
        mStopView = new ImageView(context);
        mProgressView.setBackgroundResource(R.drawable.progress_pull);
        mArrowView.setImageResource(R.drawable.icon_refresh);
        mStopView.setImageResource(R.drawable.icon_refresh_1);
        animationDrawable = (AnimationDrawable) mProgressView.getBackground();
        addView(mProgressView, DensityUtil.dp2px(40), DensityUtil.dp2px(40));
        addView(mArrowView, DensityUtil.dp2px(40), DensityUtil.dp2px(40));
        addView(mStopView, DensityUtil.dp2px(40), DensityUtil.dp2px(40));

        setMinimumHeight(DensityUtil.dp2px(60));

    }
    @Override
    @NonNull
    public View getView() {
        //真实的视图就是自己，不能返回null
        return this;
    }
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        //指定为平移，不能null
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int maxDragHeight) {
//        mProgressDrawable.start();//开始动画
        animationDrawable.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        //停止动画
        animationDrawable.stop();
        //隐藏动画
        mProgressView.setVisibility(GONE);
        // 隐藏停止图标
        mStopView.setVisibility(VISIBLE);
        //延迟500毫秒之后再弹回
        return 500;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                //显示下拉箭头
                mArrowView.setVisibility(VISIBLE);
                //隐藏动画
                mProgressView.setVisibility(GONE);
                // 隐藏停止图标
                mStopView.setVisibility(GONE);
                //还原箭头方向
                mArrowView.animate().rotation(0);
                break;
            case Refreshing:
                //显示加载动画
                mProgressView.setVisibility(VISIBLE);
                //隐藏箭头
                mArrowView.setVisibility(GONE);
                // 隐藏停止图标
                mStopView.setVisibility(GONE);
                break;
            case ReleaseToRefresh:
                //显示箭头改为朝上
                mArrowView.animate().rotation(180);
                break;
                default:
                    break;
        }
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}