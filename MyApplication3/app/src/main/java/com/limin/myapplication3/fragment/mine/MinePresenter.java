package com.limin.myapplication3.fragment.mine;

import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/13
 */
class MinePresenter extends BaseSubscription implements MineContract.Presenter {

    private MineContract.View view;
    private String[] stringArray = new String[]{"开启服务","侧滑删除","视频播放器","停止服务","CollapsingToolbarLayout","Top下拉布局","卡槽5","卡槽5","卡槽6",};

    MinePresenter(MineContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showAddView(stringArray);

    }

    @Override
    public void onDestroy() {

    }
}
