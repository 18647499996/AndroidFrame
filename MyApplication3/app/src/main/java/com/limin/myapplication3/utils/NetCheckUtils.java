/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.limin.myapplication3.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/10.
 * Description:
 */
public class NetCheckUtils {
  private static final int NETWORK_WIFI = 1;    // wifi network
  private static final int NETWORK_4G = 4;    // "4G" networks
  private static final int NETWORK_3G = 3;    // "3G" networks
  private static final int NETWORK_2G = 2;    // "2G" networks
  private static final int NETWORK_UNKNOWN = 5;    // unknown network
  private static final int NETWORK_NO = -1;   // no network

	private static final int NETWORK_TYPE_GSM      = 16;
	private static final int NETWORK_TYPE_TD_SCDMA = 17;
	private static final int NETWORK_TYPE_IWLAN    = 18;

	public static boolean isNetworkAvailable(Context context) {
		if (context == null) {
			return true;
		}
		try {
			ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo[] info = mgr.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}


	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}


	/**
	 * 获取当前的网络类型(WIFI,2G,3G,4G)
	 * <p>依赖上面的方法</p>
	 *
	 * @param context 上下文
	 * @return 网络类型名称
	 * <ul>
	 * <li>NETWORK_WIFI   </li>
	 * <li>NETWORK_4G     </li>
	 * <li>NETWORK_3G     </li>
	 * <li>NETWORK_2G     </li>
	 * <li>NETWORK_UNKNOWN</li>
	 * <li>NETWORK_NO     </li>
	 * </ul>
	 */
	public static String getNetworkTypeName(Context context) {
		switch (getNetworkType(context)) {
			case NETWORK_WIFI:
				return "WIFI";
			case NETWORK_4G:
				return "4G";
			case NETWORK_3G:
				return "4G";
			case NETWORK_2G:
				return "2G";
			case NETWORK_NO:
				return "NO";
			default:
				return "UNKNOWN";
		}
	}

	public static int getNetworkTypeNameInt(Context context) {
		switch (getNetworkType(context)) {
			case NETWORK_WIFI:
				return 1;
			case NETWORK_4G:
				return 2;
			case NETWORK_3G:
			case NETWORK_2G:
			case NETWORK_NO:
			default:
				return 0;
		}
	}

	/**
	 * 获取活动网络信息
	 * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
	 *
	 * @param context 上下文
	 * @return NetworkInfo
	 */
	private static NetworkInfo getActiveNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	/**
	 * 获取当前的网络类型(WIFI,2G,3G,4G)
	 * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
	 *
	 * @param context 上下文
	 * @return 网络类型
	 * <ul>
	 * <li>{@link #NETWORK_WIFI   } = 1;</li>
	 * <li>{@link #NETWORK_4G     } = 4;</li>
	 * <li>{@link #NETWORK_3G     } = 3;</li>
	 * <li>{@link #NETWORK_2G     } = 2;</li>
	 * <li>{@link #NETWORK_UNKNOWN} = 5;</li>
	 * <li>{@link #NETWORK_NO     } = -1;</li>
	 * </ul>
	 */
	public static int getNetworkType(Context context) {
		int netType = NETWORK_NO;
		NetworkInfo info = getActiveNetworkInfo(context);
		if (info != null && info.isAvailable()) {

			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				netType = NETWORK_WIFI;
			} else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
				switch (info.getSubtype()) {

					case NETWORK_TYPE_GSM:
					case TelephonyManager.NETWORK_TYPE_GPRS:
					case TelephonyManager.NETWORK_TYPE_CDMA:
					case TelephonyManager.NETWORK_TYPE_EDGE:
					case TelephonyManager.NETWORK_TYPE_1xRTT:
					case TelephonyManager.NETWORK_TYPE_IDEN:
						netType = NETWORK_2G;
						break;

					case NETWORK_TYPE_TD_SCDMA:
					case TelephonyManager.NETWORK_TYPE_EVDO_A:
					case TelephonyManager.NETWORK_TYPE_UMTS:
					case TelephonyManager.NETWORK_TYPE_EVDO_0:
					case TelephonyManager.NETWORK_TYPE_HSDPA:
					case TelephonyManager.NETWORK_TYPE_HSUPA:
					case TelephonyManager.NETWORK_TYPE_HSPA:
					case TelephonyManager.NETWORK_TYPE_EVDO_B:
					case TelephonyManager.NETWORK_TYPE_EHRPD:
					case TelephonyManager.NETWORK_TYPE_HSPAP:
						netType = NETWORK_3G;
						break;

					case NETWORK_TYPE_IWLAN:
					case TelephonyManager.NETWORK_TYPE_LTE:
						netType = NETWORK_4G;
						break;
					default:

						String subtypeName = info.getSubtypeName();
						if (subtypeName.equalsIgnoreCase("TD-SCDMA")
								|| subtypeName.equalsIgnoreCase("WCDMA")
								|| subtypeName.equalsIgnoreCase("CDMA2000")) {
							netType = NETWORK_3G;
						} else {
							netType = NETWORK_UNKNOWN;
						}
						break;
				}
			} else {
				netType = NETWORK_UNKNOWN;
			}
		}
		return netType;
	}
}
