package com.limin.myapplication3.activity.gaodemap;

import android.graphics.BitmapFactory;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.poisearch.PoiSearch;
import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.utils.HanlderUtils;
import com.limin.myapplication3.utils.LocationUtils;
import com.yinglan.scrolllayout.ScrollLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
class GaoDeMapPresenter extends BaseSubscription implements GaoDeMapConstract.Presenter {

    private boolean isFirstLoc = true;


    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stop();
        HanlderUtils.getInstance().clearHanlder();
    }

    @Override
    public AMap settingBaiduMapOption(MapView mapView) {
        AMap aMap = mapView.getMap();
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //定位一次，且将视角移动到地图中心点。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
        myLocationStyle.showMyLocation(true);
        // 设置圆形区域（以定位位置为圆心，定位半径的圆形区域）的边框宽度。
        myLocationStyle.strokeWidth(0);
        // 设置圆形区域（以定位位置为圆心，定位半径的圆形区域）的填充颜色。
        myLocationStyle.radiusFillColor(0);
        // 设置定位（当前位置）的icon图标。
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.img_navigation)));
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        // 矢量地图模式（基本模式）
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        // 可触发定位并显示当前位置
        aMap.setMyLocationEnabled(true);
        // 设置缩放按钮是否可见。
        aMap.getUiSettings().setZoomControlsEnabled(false);
        // 设置旋转手势是否可用。
        aMap.getUiSettings().setRotateGesturesEnabled(false);
        // 设置倾斜手势是否可用。
        aMap.getUiSettings().setTiltGesturesEnabled(false);
        // 设置是否以地图中心点缩放
        aMap.getUiSettings().setGestureScaleByMapCenter(true);

        return aMap;
    }

    @Override
    public void settingScroll(ScrollLayout activityBaiduMapScroll) {
        activityBaiduMapScroll.setMinOffset(120);
        activityBaiduMapScroll.setMaxOffset(600);
        // 最低部退出状态时可看到的高度，0为不可见
        activityBaiduMapScroll.setExitOffset(90);
        activityBaiduMapScroll.setIsSupportExit(true);
        // 是否支持横向滚动
        activityBaiduMapScroll.setAllowHorizontalScroll(true);
        activityBaiduMapScroll.setToExit();
        activityBaiduMapScroll.getBackground().setAlpha(0);
    }

    @Override
    public void settingBaiduMapLocation(AMap aMap) {
        LocationUtils.getInstance().getLocationUtils(getContext(), true).registerLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                LatLng latLng = LocationUtils.getInstance().bdDecrypt(116.4133539, 39.9806884);
                float v = (float) LocationUtils.getInstance().getDistance(latLng.longitude, latLng.latitude, aMapLocation.getLongitude(), aMapLocation.getLatitude());
                LogUtils.d("定位距离：" + v);
                LogUtils.d("定位信息：" + "\n" +
                        aMapLocation.getProvince() + "\n" +
                        aMapLocation.getCity() + "\n" +
                        aMapLocation.getCityCode() + "\n" +
                        aMapLocation.getAddress() + "\n" +
                        aMapLocation.getAdCode() + "\n" +
                        aMapLocation.getLatitude() + "\n" +
                        aMapLocation.getLongitude() + "\n" +
                        aMapLocation.getDistrict() + "\n" +
                        aMapLocation.getStreet() + aMapLocation.getStreetNum() + "\n" +
                        aMapLocation.getTime() + "\n" +
                        aMapLocation.getLocationType() + "\n" +
                        aMapLocation.getPoiName() + "\n" +
                        aMapLocation.getAoiName() + "\n" +
                        aMapLocation.getLocationDetail());
                if (isFirstLoc) {
                    isFirstLoc = false;
                    HanlderUtils.getInstance().delayExecute(() -> aMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(
                                    new CameraPosition(
                                            new LatLng(
                                                    aMapLocation.getLatitude(),
                                                    aMapLocation.getLongitude()),
                                            18,
                                            0,
                                            0)),
                            1000,
                            null
                    ), 800);
                }
            }
        }).start();
    }

    @Override
    public void onCameraChangeFinish(double longitude, double latitude, PoiSearch poiSearch) {
        // 使用反地理位置编码
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 500));
        PoiSearch.Query query = new PoiSearch.Query("", "120000|150000|050000", "");
        query.setPageSize(20);
        poiSearch.setQuery(query);
        poiSearch.searchPOIAsyn();
    }
}
