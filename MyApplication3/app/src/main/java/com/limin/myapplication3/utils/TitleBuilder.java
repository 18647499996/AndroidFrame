package com.limin.myapplication3.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.limin.myapplication3.R;


/**
 * ============================================================
 * <p/>
 * 版 权 ： 诺易(北京)科技服务有限公司
 * <p/>
 * 作 者 ： Li_Min
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 * 创建日期 ：2017/3/17
 * <p/>
 * 描 述 ： 通用标题栏
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class TitleBuilder {

    /**
     * 标题
     */
    private View titleView;

    /**
     * 左边相对布局
     */
    private RelativeLayout mRelLeft;

    /**
     * 左边TextView
     */
    private TextView mTvLeft;

    /**
     * 左边ImageView资源
     */
    private ImageView mImgLeft;

    /**
     * 中间TextView标题
     */
    private TextView mTvContent;

    /**
     * 右边相对布局
     */
    private RelativeLayout mRelRight;

    /**
     * 右边TextView
     */
    private TextView mTvRight;

    /**
     * 右边ImageView资源
     */
    private ImageView mImgRight;

    private Context context;


    /**
     * 第一种  初始化方式
     * 这里是直接引用进文件的初始化方式
     * @param context 上下文
     */
    public TitleBuilder(Activity context) {
        this.context = context;
        titleView = context.findViewById(R.id.act_title_bor);

        mTvContent = (TextView) titleView.findViewById(R.id.act_title_center_tv_title);

        mRelLeft = (RelativeLayout) titleView.findViewById(R.id.act_title_left_rel);
        mImgLeft = (ImageView) titleView.findViewById(R.id.act_title_left_img);
        mTvLeft = (TextView) titleView.findViewById(R.id.act_title_left_tv);


        mRelRight = (RelativeLayout) titleView.findViewById(R.id.act_title_right_rel);
        mTvRight = (TextView) titleView.findViewById(R.id.act_title_right_tv);
        mImgRight = (ImageView) titleView.findViewById(R.id.act_title_right_img);

    }

    public TitleBuilder(Activity activity,View view) {
        this.context = activity;
        titleView = view.findViewById(R.id.act_title_bor);

        mTvContent = (TextView) titleView.findViewById(R.id.act_title_center_tv_title);

        mRelLeft = (RelativeLayout) titleView.findViewById(R.id.act_title_left_rel);
        mImgLeft = (ImageView) titleView.findViewById(R.id.act_title_left_img);
        mTvLeft = (TextView) titleView.findViewById(R.id.act_title_left_tv);


        mRelRight = (RelativeLayout) titleView.findViewById(R.id.act_title_right_rel);
        mTvRight = (TextView) titleView.findViewById(R.id.act_title_right_tv);
        mImgRight = (ImageView) titleView.findViewById(R.id.act_title_right_img);
    }

    /**
     * title 的设置
     * @param text 设置文本
     */

    public TitleBuilder setMiddleTitleBgRes(String text) {
        setMiddleTitleBgRes(text, R.color.with,R.color.black);
        return this;
    }

    /**
     * title
     * @param text 设置文本
     * @param textColor 字体颜色
     * @param titleColor 标题背景颜色
     */
    public TitleBuilder setMiddleTitleBgRes(String text, int textColor, int titleColor) {
        mTvContent.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(text)){
            mTvContent.setText(text);
            mTvContent.setTextColor(context.getResources().getColor(textColor));
            titleView.setBackgroundColor(context.getResources().getColor(titleColor));
        }
        return this;
    }

    /**
     * left图片按钮
     * @param resId 资源id
     */
    public TitleBuilder setLeftImageRes(int resId) {
        mImgLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mImgLeft.setImageResource(resId);
        return this;
    }

    /**
     * 设置左边TextView
     * @param text 文本
     * @return
     */
    public TitleBuilder setLeftTextRes(String text){
        setLeftTextRes(text,14,R.color.with);

        return this;
    }

    /**
     * 设置左边TextView
     * @param text 文本
     * @param textSize 字体大小
     * @param textColor 字体颜色
     */
    public TitleBuilder setLeftTextRes(String text, int textSize, int textColor) {
        mTvLeft.setVisibility(TextUtils.isEmpty(text)? View.GONE:View.VISIBLE);
        if (!TextUtils.isEmpty(text)){
            mTvLeft.setText(text);
            mTvLeft.setTextSize(textSize);
            mTvLeft.setTextColor(context.getResources().getColor(textColor));
        }
        return this;
    }

    /**
     * 设置左边的事件
     * @param activity activity引用
     */
    public TitleBuilder setLeftRelativeLayoutListener(Activity activity) {
        if (mRelLeft.getVisibility() == View.VISIBLE) {
            mRelLeft.setOnClickListener(v -> {
                if (!activity.isFinishing()){
                    activity.finish();
                }
            });
        }
        return this;
    }

    /**
     * right右边图片按钮
     * @param resId 资源id
     */
    public TitleBuilder setRightImageRes(int resId) {
        mImgRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mImgRight.setImageResource(resId);
        return this;
    }

    /**
     * 右边文字按钮
     * @param text 文本
     */
    public TitleBuilder setRightText(String text) {
        setRightText(text,18,R.color.with);
        return this;
    }

    /**
     * 右边文本
     * @param text 文本
     * @param textSize 字体大小
     * @param textColor 字体颜色
     */
    public TitleBuilder setRightText(String text, int textSize, int textColor) {
        mTvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE: View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            mTvRight.setText(text);
            mTvRight.setTextSize(textSize);
            mTvRight.setTextColor(context.getResources().getColor(textColor));
        }
        return this;
    }

    /**
     * 设置右边的事件
     */
    public TitleBuilder setRightRelativeLayoutListener(View.OnClickListener listener) {
        if (mRelRight.getVisibility() == View.VISIBLE) {
            mRelRight.setOnClickListener(listener);
        }
        return this;
    }
}
