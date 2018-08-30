package com.limin.myapplication3.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Description 电商pageAdapter页面（Activity）
 *
 * @author wangzhongliang
 *         Time：2017/12/25
 */

public class TabAdapter extends FragmentStatePagerAdapter {
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
