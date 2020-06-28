package com.limin.myapplication3.model;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/18
 */
public class LocationMarkerModel {

    private String address;
    private double lng;
    private double lat;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
