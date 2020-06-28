package com.limin.myapplication3.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
public class LocationUtils {

    private AMapLocationClient aMapLocationClient;

    private static volatile LocationUtils instance = null;

    private LocationUtils() {
    }

    public static LocationUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (LocationUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new LocationUtils();
                }
            }
        }
        return instance;
    }


    public LocationUtils getLocationUtils(Context context, boolean isOnceLocation) {
        if (aMapLocationClient != null) {
            return this;
        }
        aMapLocationClient = new AMapLocationClient(context);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setGpsFirst(true);
        mLocationOption.setInterval(60000);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        aMapLocationClient.setLocationOption(mLocationOption);
        return this;
    }

    public LocationUtils registerLocationListener(AMapLocationListener locationChangedListener) {
        aMapLocationClient.setLocationListener(locationChangedListener);
        return this;
    }

    public void start() {
        if (aMapLocationClient != null) {
            aMapLocationClient.startLocation();
        }
    }

    public void stop() {
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
            aMapLocationClient = null;
        }
    }

    public void startIndoorMode() {

    }

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lng1 经度1
     * @param lat1 纬度1
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return 距离
     */
    public double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        double earthRadius = 6378.137;
        s = s * earthRadius;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 百度坐标转高德（传入经度、纬度）
     * @param lng 百度经度
     * @param lat 百度纬度
     * @return LatLng
     */
    public LatLng bdDecrypt(Double lng, Double lat) {
        double X_PI = Math.PI * 3000.0 / 180.0;
        double x = lng - 0.0065;
        double y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
        double newLng = z * Math.cos(theta);
        double newLat = z * Math.sin(theta);
        return new LatLng(newLat, newLng);
    }

    /**
     * 高德坐标转百度（传入经度、纬度）
     * @param lng 高德经度
     * @param lat 高德纬度
     * @return LatLng
     */
    public LatLng bdEncrypt(double lng, double lat) {
        double X_PI = Math.PI * 3000.0 / 180.0;
        double x = lng, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * X_PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * X_PI);
        double newlng = z * Math.cos(theta) + 0.0065;
        double newlat = z * Math.sin(theta) + 0.006;
        return new LatLng(newlat, newlng);
    }

}
