package com.limin.myapplication3.utils;

import android.app.Activity;
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


    /**
     * 第一种  初始化方式
     * 这里是直接引用进文件的初始化方式
     * @param context 上下文
     */
    public TitleBuilder(Activity context) {

        titleView = context.findViewById(R.id.act_title_bor);

        mTvContent = titleView.findViewById(R.id.act_title_center_tv_title);

        mRelLeft = titleView.findViewById(R.id.act_title_left_rel);
        mImgLeft = titleView.findViewById(R.id.act_title_left_img);


        mRelRight = titleView.findViewById(R.id.act_title_right_rel);
        mTvRight = titleView.findViewById(R.id.act_title_right_tv);
        mImgRight =titleView.findViewById(R.id.act_title_right_img);

    }

    /**
     * title 的设置
     * @param resid 资源
     * @param text 设置文本
     * @param size 字体大小
     */

    public TitleBuilder setMiddleTitleBgRes(int resid, String text, int size) {
        mTvContent.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        mTvContent.setTextColor(resid);
        mTvContent.setText(text);
        mTvContent.setTextSize(size);
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
     * 设置左边的事件
     * @param listener 监听回调
     */
    public TitleBuilder setLeftRelativeLayoutListener(View.OnClickListener listener) {
        if (mRelLeft.getVisibility() == View.VISIBLE) {
            mRelLeft.setOnClickListener(listener);
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
    public TitleBuilder setRightText(String text, int color, int size) {
        mTvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE: View.VISIBLE);
        mTvRight.setText(text);
        mTvRight.setTextSize(size);
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
