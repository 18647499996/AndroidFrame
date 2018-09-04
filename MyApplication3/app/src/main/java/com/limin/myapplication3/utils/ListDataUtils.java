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

    private static Integer[] integerArray = new Integer[]{R.drawable.bg_machine,R.drawable.bg_machine_type1,R.drawable.bg_machine_noon,R.drawable.bg_machine_night,R.drawable.bg_machine_type1};
    private static String[] stringArray = new String[]{"天龙八部","射雕英雄传","神雕侠侣","倚天屠龙记","天龙八部"};
    private static String[] stringArrayTwo = new String[]{"东北大米","金龙鱼调和油","五谷小米","粗粮杂粮","东北大米"};
    private static String[] stringArrayThree = new String[]{"新疆特产","葡萄干","新疆巴旦豆","新疆核桃","新疆特产"};
    private static String[] stringArrayFour = new String[]{"星球大盘鸡","红枣咖啡","核桃咖啡","小巴扎情人茶","星球大盘鸡"};

    private static Integer[] integerTypeArray = new Integer[]{1,2,0,3,5};

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
            DataModel dataModel = new DataModel(
                    stringArray[i],
                    stringArrayTwo[i],
                    stringArrayThree[i],
                    stringArrayFour[i],
                    integerArray[i],
                    integerTypeArray[i]
            );
            dataModelList.add(dataModel);
        }
        return dataModelList;
    }
}
