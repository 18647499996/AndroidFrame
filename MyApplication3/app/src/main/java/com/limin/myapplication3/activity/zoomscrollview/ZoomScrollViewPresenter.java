package com.limin.myapplication3.activity.zoomscrollview;

import com.limin.myapplication3.model.VitaeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/12/5
 */
class ZoomScrollViewPresenter implements ZoomScrollViewConstract.Presenter {

    private ZoomScrollViewConstract.View view;
    private String[] stringArray = new String[]{"发展路径：","当前职级：","资格认证：","直接师傅：","间接师傅：","手机号码：","健康证截止时间：","发展路径：","当前职级：","资格认证：","直接师傅：","间接师傅：","手机号码：","健康证截止时间："};

    ZoomScrollViewPresenter(ZoomScrollViewConstract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void queryData() {
        List<VitaeModel> vitaeModelList = new ArrayList<>();
        for (String aStringArray : stringArray) {
            VitaeModel vitaeModel = new VitaeModel();
            vitaeModel.setName(aStringArray);
            vitaeModelList.add(vitaeModel);
        }
        view.showListData(vitaeModelList);
    }
}
