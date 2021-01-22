package com.limin.myapplication3.utils;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AppUtil {

  public enum NetState {NET_NO, NET_2G, NET_3G, NET_4G, NET_WIFI, NET_UNKNOWN, NET_MOBILE}

  /**
   * 检查系统中是否安装了某个应用
   *
   * @param packageName 应用的包名
   * @return true 表示已安装，否则返回false
   */
  public static boolean isInstalled(Context context, String packageName) {
    PackageManager packageManager = context.getPackageManager();// 获取packagemanager
    List<PackageInfo> installedList = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
    Iterator<PackageInfo> iterator = installedList.iterator();

    PackageInfo info;
    String name;
    while (iterator.hasNext()) {
      info = iterator.next();
      name = info.packageName;
      if (name.equals(packageName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 检查系统中是否安装了某个应用
   */
  public static boolean isApkInstalled(Context context, String packageName) {
    PackageManager packageManager = context.getPackageManager();// 获取packagemanager
    try {
      packageManager.getPackageInfo(packageName, 0);
    } catch (PackageManager.NameNotFoundException e) {
      return false;
    }
    return true;
  }

  /**
   * 检查系统中是否安装了某个应用
   */
  public static boolean isInstalled(String packageName) {
    String packages = "package:" + packageName;
    long ss = System.currentTimeMillis();
    Process process = null;
    BufferedReader bis = null;
    try {
      process = Runtime.getRuntime().exec("pm list packages -3");//adb shell pm list package -3
      bis = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = null;
      while ((line = bis.readLine()) != null) {
        if (line != null && line.equals(packages)) {
          bis = null;
          process.destroy();
          return true;
        }
      }
    } catch (IOException e) {
      Log.i("IOException", "isInstalled: " + e);
    } finally {
      try {
        if (bis != null) {
          bis.close();
        }
        if (process != null) {
          process.destroy();
        }
      } catch (Exception ignored) {
      }
    }
    return false;
  }

  //@SuppressLint("MissingPermission")
  //public static String getDeviceId(Context context) {
  //  String imei = null;
  //  TelephonyManager telephonyMgr =
  //      (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
  //  if (telephonyMgr != null) {
  //    imei = telephonyMgr.getDeviceId();
  //  }
  //  return imei;
  //}

  /**
   * 根据 url获取文件名
   */
  public static String getUrlName(String url) {
    String urlName = "";
    int last = url.lastIndexOf("/") + 1;
    urlName = url.substring(last);
    if (urlName.contains(".apk")) {
      urlName = urlName.substring(0, urlName.length() - 4);
    }
    urlName = md5(urlName);
    return urlName;
  }

  public static String getLastName(String url) {
    String urlName = "";
    int last = url.lastIndexOf("/") + 1;
    urlName = url.substring(last);
    if (!urlName.contains(".apk")) {
      if (urlName.length() > 10) {
        urlName = urlName.substring(urlName.length() - 10);
      }
      urlName += ".apk";
    }
    return urlName;
  }

  /**
   * 根据taskid获取文件名
   */
  public static String getNameByTaskId(Context context, long taskId) {
    String downName = "";
    DownloadManager.Query query = new DownloadManager.Query();
    query.setFilterById(taskId);//筛选下载任务，传入任务ID，可变参数
    DownloadManager downloadManager =
        (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    Cursor c = downloadManager.query(query);
    c.moveToFirst();
    if (c.moveToFirst()) {
      //            status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
      downName = c.getString(c.getColumnIndex(DownloadManager.COLUMN_TITLE));
    }
    c.close();
    return downName;
  }

  public static void showToast(Context context, String content) {
    try {
      Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 检查系统下载器是否可用
   */
  public static boolean canDownloadState(Context context) {
    try {
      int state = context.getPackageManager()
          .getApplicationEnabledSetting("com.android.providers.downloads");

      if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED
          || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
          || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {

        return false;
      }
    } catch (Exception e) {
      //            e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * 启动指定包名的APP
   */
  public static void startAppByPackageName(Context context, String packagename) {
    if (TextUtils.isEmpty(packagename)) {
      return;
    }
    PackageManager packageManager = context.getPackageManager();
    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
    PackageInfo packageinfo = null;
    try {
      packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    if (packageinfo == null) {
      return;
    }
    Intent intent = packageManager.getLaunchIntentForPackage(packagename);
    if (intent != null) {
      intent.addCategory(Intent.CATEGORY_LAUNCHER);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
          | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
      context.startActivity(intent);
    } else {
      try {
        Toast.makeText(context, "手机还未安装该应用", Toast.LENGTH_SHORT).show();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 在外部浏览器中打开给定链接
   */
  public static void jumpToBrowser(Context context, String url) {

    Intent intent = new Intent();
    intent.setAction("android.intent.action.VIEW");//Intent.ACTION_VIEW
    Uri content_url = Uri.parse(url);
    intent.setData(content_url);
    if (intent.resolveActivity(context.getPackageManager()) != null) {
      final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
      context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
    } else {
      try {
        Toast.makeText(context, "没有匹配的程序", Toast.LENGTH_SHORT).show();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    //        startActivity(intent);
  }

  /**
   * 跳转到设置界面
   */
  public static void jumpSetting(Context context) {
    String packageName = "com.android.providers.downloads";
    try {
      //Open the specific App Info page:
      Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
      intent.setData(Uri.parse("package:" + packageName));
      context.startActivity(intent);
    } catch (ActivityNotFoundException e) {
      //e.printStackTrace();
      //Open the generic Apps page:
      Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
      context.startActivity(intent);
    }
  }

  /**
   * 跳转到权限设置界面
   */
  public static void jumpPermissionSetting(Context context) {
    Intent intent = new Intent();
    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.addCategory(Intent.CATEGORY_DEFAULT);
    intent.setData(Uri.parse("package:" + context.getPackageName()));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
    context.startActivity(intent);
  }

  public static boolean hasSD() {
    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
  }

  /**
   * 获取md5码
   */

  public static String md5(String string) {
    if (TextUtils.isEmpty(string)) {
      return "";
    }
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(string.getBytes());
      String result = "";
      for (byte b : bytes) {
        String temp = Integer.toHexString(b & 0xff);
        if (temp.length() == 1) {
          temp = "0" + temp;
        }
        result += temp;
      }
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 复制单个文件
   *
   * @param oldPath String 原文件路径 如：c:/fqf.txt
   * @param newPath String 复制后路径 如：f:/fqf.txt
   * @return boolean
   */
  public static void copyFile(String oldPath, String newPath) {
    try {
      int bytesum = 0;
      int byteread = 0;
      File oldfile = new File(oldPath);
      if (oldfile.exists()) { //文件存在时
        InputStream inStream = new FileInputStream(oldPath); //读入原文件
        FileOutputStream fs = new FileOutputStream(newPath);
        byte[] buffer = new byte[1444];
        int length;
        while ((byteread = inStream.read(buffer)) != -1) {
          bytesum += byteread; //字节数 文件大小
          //                    System.out.println(bytesum);
          fs.write(buffer, 0, byteread);
        }
        inStream.close();
      }
    } catch (Exception e) {
      //            System.out.println("复制单个文件操作出错");
      e.printStackTrace();
    }
  }

  public static NetState getNetType(Context context) {
    NetState stateCode = NetState.NET_NO;
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo ni = cm.getActiveNetworkInfo();
    if (ni != null && ni.isConnectedOrConnecting()) {
      switch (ni.getType()) {
        case ConnectivityManager.TYPE_WIFI:
          stateCode = NetState.NET_WIFI;
          break;
        case ConnectivityManager.TYPE_MOBILE:
          switch (ni.getSubtype()) {
            case TelephonyManager.NETWORK_TYPE_GPRS: //联通2g
            case TelephonyManager.NETWORK_TYPE_CDMA: //电信2g
            case TelephonyManager.NETWORK_TYPE_EDGE: //移动2g
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
              stateCode = NetState.NET_2G;
              break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A: //电信3g
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
              stateCode = NetState.NET_3G;
              break;
            case TelephonyManager.NETWORK_TYPE_LTE:
              stateCode = NetState.NET_4G;
              break;
            default:
              stateCode = NetState.NET_UNKNOWN;
          }
          break;
        default:
          stateCode = NetState.NET_UNKNOWN;
      }
    }
    return stateCode;
  }

  public static NetState getNetWorkType(Context context) {
    NetState stateCode = NetState.NET_NO;
    try {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      if (cm != null) {
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
          switch (ni.getType()) {
            case ConnectivityManager.TYPE_WIFI:
              stateCode = NetState.NET_WIFI;
              break;
            case ConnectivityManager.TYPE_MOBILE:
              stateCode = NetState.NET_MOBILE;
              break;
            default:
              stateCode = NetState.NET_UNKNOWN;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stateCode;
  }

  /**
   * 设置视图定时隐藏
   *
   * @param view 需要展示隐藏的视图
   * @param second 展示时间
   */
  public static void showViewWithHide(View view, int second) {
    showViewWithHide(view, second, null);
  }

  /**
   * 设置视图定时隐藏
   *
   * @param view 需要展示隐藏的视图
   * @param second 展示时间
   */
  public static void showViewWithHide(View view, int second, Runnable runnable) {
    if (view == null) {
      if (runnable != null) {
        runnable.run();
      }
      return;
    }
    view.setVisibility(View.VISIBLE);
    Flowable.intervalRange(
        0, second + 1,
        0, 1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete(() -> {
          view.setVisibility(View.GONE);
          if (runnable != null) {
            runnable.run();
          }
        })
        .subscribe();
  }

  /**
   *   * 获取系统的语言类型
   *   * @return
   *  
   */
  public static String getLocaleLanguage() {
      Locale l = Locale.getDefault();
      return String.format("%s-%s", l.getLanguage(), l.getCountry());
  }

  /**
   * 获取用户注册时间 秒转毫秒
   *  
   */
//  public static String getRegTime(UserInfo userInfo){
//    String reg_time = "";
//    if(userInfo != null){
//      reg_time =  userInfo.reg_time;
//    } else {
//      reg_time = SP2Util.getString(SPK.USER_REG_TIME, "");
//    }
//
//    if (!TextUtils.isEmpty(reg_time)) {
//      reg_time += "000";
//    }
//    return reg_time;
//  }

  public static int getTwoNumber() {
    return (int) (10 + Math.random() * (99 - 10 + 1));
  }

  public static int getThreeNumber() {
    return (int) (100 + Math.random() * (999 - 100 + 1));
  }
}