package com.limin.myapplication3.model;

import com.orient.tea.barragephoto.model.DataSource;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/18
 */
public class BarrageModel implements DataSource {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getType() {
        return 0;
    }
}
