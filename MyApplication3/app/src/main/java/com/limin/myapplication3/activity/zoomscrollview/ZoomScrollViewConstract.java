package com.limin.myapplication3.activity.zoomscrollview;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.limin.myapplication3.model.VitaeModel;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/12/5
 */
interface ZoomScrollViewConstract {
    interface View extends BaseView<Presenter> {

        void showListData(List<VitaeModel> vitaeModelList);
    }

    interface Presenter extends BasePresenter {

        void queryData();
    }
}
