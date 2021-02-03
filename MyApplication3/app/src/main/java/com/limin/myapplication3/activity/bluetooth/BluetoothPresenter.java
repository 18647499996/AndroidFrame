package com.limin.myapplication3.activity.bluetooth;

import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.utils.BluetoothUtils;

import javax.crypto.Mac;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/20
 */
class BluetoothPresenter extends BaseSubscription<BluetoothConstract.View> implements BluetoothConstract.Presenter {

    private BluetoothUtils bluetoothUtils;

    protected BluetoothPresenter(BluetoothConstract.View view) {
        super(view);
        bluetoothUtils = BluetoothUtils.getInstance();
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void getBluetoothDeviceList(BluetoothActivity bluetoothActivity) {
        bluetoothUtils.init(bluetoothActivity);
        bluetoothUtils.searchBluetooth();
    }
}
