package com.limin.myapplication3.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description 电商pageAdapter页面（Activity）
 *
 * @author wangzhongliang
 *         Time：2017/12/25
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;
    private String[] mTitles;

    public TabAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.mList = list;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }


    public void setmList(List<Fragment> mList) {
        this.mList = mList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    public void destoryOperate(){
        if(mList !=null){
            mList.clear();
            mList = null;
        }
        mTitles = null;
    }
}
