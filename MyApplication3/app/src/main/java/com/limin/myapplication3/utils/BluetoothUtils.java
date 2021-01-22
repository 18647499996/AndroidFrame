package com.limin.myapplication3.utils;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.beacon.Beacon;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/20
 */
public class BluetoothUtils {

    private BluetoothClient mClient;

    private SearchRequest request;

    private static volatile BluetoothUtils instance = null;

    private BluetoothUtils() {
    }

    public static BluetoothUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (BluetoothUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new BluetoothUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化蓝牙
     */
    public void init(Context context) {
        mClient = new BluetoothClient(context);
        request = new SearchRequest.Builder()
                // 先扫BLE设备3次，每次3s
                .searchBluetoothLeDevice(3000, 3)
                // 再扫经典蓝牙5s
                .searchBluetoothClassicDevice(5000)
                // 再扫BLE设备2s
                .searchBluetoothLeDevice(2000)
                .build();
    }

    public void searchBluetooth(){
        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {

            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Beacon beacon = new Beacon(device.scanRecord);
                if (!TextUtils.equals(device.getName(),"NULL")) {
                    LogUtils.v("设备MAC地址：" + device.getAddress() +
                            "\n" + "设备名称：" + device.getName() + "设备详情List：" + beacon.mItems.toString() +
                            "设备详情toString：" + beacon.toString());
                }
            }

            @Override
            public void onSearchStopped() {

            }

            @Override
            public void onSearchCanceled() {

            }
        });
    }
}
