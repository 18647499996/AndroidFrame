package com.limin.myapplication3.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/30
 */
public class DataModel implements MultiItemEntity {

    private String name;
    private String nametwo;
    private String namethree;
    private String namefour;
    private int icon;
    private int type;

    public DataModel() {

    }

    public DataModel(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public DataModel(String name, int icon, int type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }


    public DataModel(String name, String nametwo, String namethree, String namefour, int icon, int type) {
        this.name = name;
        this.nametwo = nametwo;
        this.namethree = namethree;
        this.namefour = namefour;
        this.icon = icon;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNametwo() {
        return nametwo;
    }

    public void setNametwo(String nametwo) {
        this.nametwo = nametwo;
    }

    public String getNamethree() {
        return namethree;
    }

    public void setNamethree(String namethree) {
        this.namethree = namethree;
    }

    public String getNamefour() {
        return namefour;
    }

    public void setNamefour(String namefour) {
        this.namefour = namefour;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
