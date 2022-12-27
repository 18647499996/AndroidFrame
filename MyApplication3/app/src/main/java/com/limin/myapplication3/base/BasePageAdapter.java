package com.limin.myapplication3.base;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/7
 */
public class BasePageAdapter extends FragmentPagerAdapter {

    List<Fragment> mList;
    Context context;


    public BasePageAdapter(FragmentManager fm, List<Fragment> mList , Context context) {
        super(fm);
        this.mList = mList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
