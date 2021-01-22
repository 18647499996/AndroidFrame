package com.limin.myapplication3.activity.bluetooth;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/20
 */
interface BluetoothConstract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        /**
         * 获取蓝牙设备列表
         * @param bluetoothActivity activity引用
         */
        void getBluetoothDeviceList(BluetoothActivity bluetoothActivity);
    }
}
