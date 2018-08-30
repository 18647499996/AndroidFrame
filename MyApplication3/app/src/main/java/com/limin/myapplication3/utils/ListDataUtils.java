package com.limin.myapplication3.utils;

import android.support.v4.app.Fragment;

import com.limin.myapplication3.R;
import com.limin.myapplication3.fragment.forum.ForumFragment;
import com.limin.myapplication3.fragment.home.HomeFragment;
import com.limin.myapplication3.fragment.issue.IssueFragment;
import com.limin.myapplication3.fragment.mine.MineFragment;
import com.limin.myapplication3.model.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class ListDataUtils {

    private static Integer[] integerArray = new Integer[]{R.drawable.bg_machine,R.drawable.bg_machine_type1,R.drawable.bg_machine_noon,R.drawable.bg_machine_night};
    private static String[] stringArray = new String[]{"天龙八部","射雕英雄传","神雕侠侣","倚天屠龙记"};

    public static String[] titleArray() {
        String[] strings = new String[]{"首页","论坛","问题","我的"};
        return strings;
    }

    public static List<Fragment> mainFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ForumFragment());
        fragmentList.add(new IssueFragment());
        fragmentList.add(new MineFragment());
        return fragmentList;
    }

    /**
     * 设置首页数据
     * @return dataModelList
     */
    public static List<DataModel> iconDataList() {
        List<DataModel> dataModelList = new ArrayList<>();
        for (int i = 0; i < integerArray.length; i++) {
            DataModel dataModel = new DataModel(stringArray[i],integerArray[i]);
            dataModelList.add(dataModel);
        }
        return dataModelList;
    }
}
