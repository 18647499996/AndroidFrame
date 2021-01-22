package com.limin.myapplication3.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.limin.myapplication3.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/8/19
 */
public class MagicIndicatorUtils {

    private static volatile MagicIndicatorUtils instance = null;

    private MagicIndicatorUtils(){}

    public static MagicIndicatorUtils getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (MagicIndicatorUtils.class){
            // double checkout
            if(null == instance){
                instance = new MagicIndicatorUtils();
            }
        }
     }
     return instance;
    }

    /**
     * 初始化默认标题导航配置
     * @param activity activity引用
     * @param stringArray 导航数据
     * @param activityClockRecordMagicIndicator 导航组件
     * @param activityCouponVp ViewPager组件
     * @param onPagerTitleChangeListener 标题导航监听事件
     */
    public void initMagicIndicatorDefaultOption(FragmentActivity activity, String[] stringArray, MagicIndicator activityClockRecordMagicIndicator, ViewPager activityCouponVp, OnPagerTitleChangeListener onPagerTitleChangeListener) {
        CommonNavigator commonNavigator = new CommonNavigator(activity);
        // 设置Tab标题自适应模式
        commonNavigator.setAdjustMode(true);
        // 设置Tab标题导航适配器
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return stringArray.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                // 设置标题样式
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                // 添加自定义布局
                View mLayout = View.inflate(context, R.layout.magic_indicator_title, null);
                TextView textView = mLayout.findViewById(R.id.magic_indicator_title_tv);
                commonPagerTitleView.setContentView(mLayout);
                textView.setText(stringArray[index]);
                commonPagerTitleView.setOnClickListener(v -> activityCouponVp.setCurrentItem(index));
                // 设置标题滚动监听
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        // 选中状态
                        textView.setTextColor(context.getResources().getColor(R.color.color_2e8fc8));
                        onPagerTitleChangeListener.onSelected(index,totalCount);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        // 默认状态
                        textView.setTextColor(context.getResources().getColor(R.color.color_342e2e));

                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        // 过渡动画效果（开始）
                        textView.setScaleX(1.2f + (1.0f - 1.2f) * leavePercent);
                        textView.setScaleY(1.2f + (1.0f - 1.2f) * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        // 过渡动画效果（结束）
                        textView.setScaleX(1.0f + (1.2f - 1.0f) * enterPercent);
                        textView.setScaleY(1.0f + (1.2f - 1.0f) * enterPercent);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                // 设置指示器样式
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                // 设置指示器线条宽度模式
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                // 设置指示器高度
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                // 设置指示器宽度
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                // 设置指示器圆角边
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                // 设置指示器开始移动过渡效果
                indicator.setStartInterpolator(new AccelerateInterpolator());
                // 设置指示器结束移动过渡效果
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                // 设置指示器颜色
                indicator.setColors(context.getResources().getColor(R.color.color_2e8fc8));
                return indicator;
            }
        });
        // 添加标题导航
        activityClockRecordMagicIndicator.setNavigator(commonNavigator);
        // 将ViewPager与标题导航绑定
        ViewPagerHelper.bind(activityClockRecordMagicIndicator, activityCouponVp);
    }



    public interface OnPagerTitleChangeListener {
        /**
         * 选中状态
         * @param index 选中索引
         * @param totalCount 总数量
         */
        void onSelected(int index, int totalCount);
    }
}
