package com.limin.myapplication3.utils;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.SPUtils;
import com.example.rxjavademo.util.Optional;
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
        SPUtils.getInstance(Constant.SPUtils.USER).put(Constant.SPUtils.USER,GsonUtils.toJson(userData));
    }

    public UserModel getUserModel() {
        String string = SPUtils.getInstance(Constant.SPUtils.USER).getString(Constant.SPUtils.USER);
        return GsonUtils.fromJson(string, UserModel.class);
    }

    public void exitLogin(){
        SPUtils.getInstance(Constant.SPUtils.USER).clear();
    }

    /**
     * 获取用户token
     * @return getToken
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getToken() {
        return Optional.of(Optional.ofNullable(getUserModel()).map(UserModel::getToken).orElse("token get fali")).get();
    }

    public String getUd(){
        return Optional.of(Optional.ofNullable(getUserModel()).map(UserModel::getUid).orElse("uid get fali")).get();
    }
}
