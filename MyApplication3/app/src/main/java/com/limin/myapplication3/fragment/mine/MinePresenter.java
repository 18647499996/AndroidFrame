package com.limin.myapplication3.fragment.mine;

import android.widget.Button;
import android.widget.TextView;

import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/13
 */
class MinePresenter extends BaseSubscription implements MineContract.Presenter {

    private MineContract.View view;
    private String[] stringArray = new String[]{"开启服务","开源视频播放器","卡槽1","卡槽2","卡槽3","卡槽4","卡槽5","卡槽5","卡槽6",};

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
