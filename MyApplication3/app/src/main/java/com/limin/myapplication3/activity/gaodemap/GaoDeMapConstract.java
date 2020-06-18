package com.limin.myapplication3.activity.gaodemap;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.services.poisearch.PoiSearch;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.yinglan.scrolllayout.ScrollLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
interface GaoDeMapConstract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        /**
         * 设置地图配置
         * @param mapView 地图配置
         */
        AMap settingBaiduMapOption(MapView mapView);

        /**
         * 设置抽屉
         * @param activityBaiduMapScroll
         */
        void settingScroll(ScrollLayout activityBaiduMapScroll);


        void settingBaiduMapLocation(AMap aMap);


        void onCameraChangeFinish(double longitude, double latitude, PoiSearch poiSearch);
    }
}
