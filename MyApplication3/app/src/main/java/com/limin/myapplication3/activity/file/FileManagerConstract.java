package com.limin.myapplication3.activity.file;

import android.support.v4.view.ViewPager;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;

import net.lucode.hackware.magicindicator.MagicIndicator;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
class FileManagerConstract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        /**
         * 设置viewpager
         * @param fileManagerActivity activity引用
         * @param activityFileManagerVp viewpager组件
         */
        void setViewPager(FileManagerActivity fileManagerActivity, ViewPager activityFileManagerVp);

        /**
         * 设置指示器
         * @param activityFileManagerMagicIndicator 指示器组件
         * @param activityFileManagerVp viewpager组件
         * @param fileManagerActivity activity引用
         */
        void setMagicIndicator(MagicIndicator activityFileManagerMagicIndicator, ViewPager activityFileManagerVp, FileManagerActivity fileManagerActivity);
    }
}
