package com.limin.myapplication3.model;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/30
 */
public class DataModel {

    private String name;
    private int icon;

    public DataModel() {

    }

    public DataModel(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
