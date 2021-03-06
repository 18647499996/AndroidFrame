package com.limin.myapplication3.model;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/12
 */
public class StaggeredModel {

    private String name;
    private int icon;
    private boolean isSeletor = false;

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

    public boolean isSeletor() {
        return isSeletor;
    }

    public void setSeletor(boolean seletor) {
        isSeletor = seletor;
    }
}