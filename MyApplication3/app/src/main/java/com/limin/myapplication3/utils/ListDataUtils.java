package com.limin.myapplication3.utils;


import androidx.fragment.app.Fragment;

import com.limin.myapplication3.R;
import com.limin.myapplication3.fragment.forum.ForumFragment;
import com.limin.myapplication3.fragment.home.HomeFragment;
import com.limin.myapplication3.fragment.issue.IssueFragment;
import com.limin.myapplication3.fragment.mine.MineFragment;
import com.limin.myapplication3.model.DataModel;
import com.limin.myapplication3.model.LocationMarkerModel;
import com.limin.myapplication3.model.StaggeredModel;

import net.sf.saxon.type.ListType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class ListDataUtils {

    private static Integer[] integerArray = new Integer[]{R.drawable.bg_machine, R.drawable.bg_machine_type1, R.drawable.bg_machine_noon, R.drawable.bg_machine_night, R.drawable.bg_machine_type1};
    private static String[] stringArray = new String[]{"天龙八部", "射雕英雄传", "神雕侠侣", "倚天屠龙记", "天龙八部"};
    private static String[] stringArrayTwo = new String[]{"东北大米", "金龙鱼调和油", "五谷小米", "粗粮杂粮", "东北大米"};
    private static String[] stringArrayThree = new String[]{"新疆特产", "葡萄干", "新疆巴旦豆", "新疆核桃", "新疆特产"};
    private static String[] stringArrayFour = new String[]{"星球大盘鸡", "红枣咖啡", "核桃咖啡", "小巴扎情人茶", "星球大盘鸡"};

    private static Integer[] integerTypeArray = new Integer[]{1, 2, 0, 3, 5};
    private static Integer[] integersIcon = new Integer[]{
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img1,
            R.drawable.img2
    };

    private static double[] doubleLatArray = new double[]{
            22.5607, 32.092461, 29.824411, 31.232111, 36.099802, 39.980766, 27.557964, 23.132616};

    private static double[] doubleLongArray = new double[]{
            113.954226, 118.757107, 121.559279, 121.523906, 120.388269, 116.413218, 118.027581, 113.326146};

    private static String[] stringNameArray = new String[]{
            "广东省深圳市南山区朗山路13号紫光信息港c906", "江苏省南京中山北路346号老学堂创意园58号楼605-606",
            "浙江省宁波市鄞州区鄞州商会大厦南楼4楼", "上海市浦东新区浦东南路1271号华融大厦10层1005",
            "青岛市市北区昆山路17号7080中心1号楼1305", "北京市朝阳区安定路35号安华发展大厦902", "福建省南平市武夷山市兴田镇黄土程家州",
            "广州市保利克洛维广场中景A座2522"};

    public static String[] titleArray() {
        String[] strings = new String[]{"首页", "论坛", "问题", "我的"};
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
     *
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

    /**
     * 设置瀑布流数据
     *
     * @return staggeredModelList
     */
    public static List<StaggeredModel> staggeredDataList() {
        List<StaggeredModel> staggeredModelList = new ArrayList<>();
        for (int i = 0; i < integersIcon.length; i++) {
            StaggeredModel staggeredModel = new StaggeredModel();
            staggeredModel.setIcon(integersIcon[i]);
            staggeredModel.setName("图片" + i);
            staggeredModelList.add(staggeredModel);
        }
        return staggeredModelList;
    }

    public static List<LocationMarkerModel> getLocationMarker(){
        List<LocationMarkerModel> locationMarkerModels = new ArrayList<>();
        for (int i = 0; i < stringNameArray.length; i++) {
            LocationMarkerModel locationMarkerModel = new LocationMarkerModel();
            locationMarkerModel.setAddress(stringNameArray[i]);
            locationMarkerModel.setLat(doubleLatArray[i]);
            locationMarkerModel.setLng(doubleLongArray[i]);
            locationMarkerModels.add(locationMarkerModel);
        }
        return locationMarkerModels;
    }

}
