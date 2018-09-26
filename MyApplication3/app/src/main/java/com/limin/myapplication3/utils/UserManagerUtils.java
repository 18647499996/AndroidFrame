package com.limin.myapplication3.utils;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.SPUtils;
import com.limin.myapplication3.model.UserModel;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/25
 */
public class UserManagerUtils {

    private static volatile UserManagerUtils instance = null;

    private UserManagerUtils(){}

    public static UserManagerUtils getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (UserManagerUtils.class){
            // double checkout
            if(null == instance){
                instance = new UserManagerUtils();
            }
        }
     }
     return instance;
    }


    /**
     * 保存用户信息
     * @param userData 用户信息
     */
    public void saveUserModel(@NonNull UserModel userData) {
        SPUtils.getInstance(Constant.USER).put(Constant.USER,GsonUtils.toJson(userData));
    }

    public UserModel getUserModel() {
        String string = SPUtils.getInstance(Constant.USER).getString(Constant.USER);
        return GsonUtils.fromJson(string, UserModel.class);
    }

    /**
     * 获取用户token
     * @return getToken
     */
    public String getToken() {
        return getUserModel().getToken();
    }
}
