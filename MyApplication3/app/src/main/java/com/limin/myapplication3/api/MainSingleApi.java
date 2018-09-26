package com.limin.myapplication3.api;

import com.limin.myapplication3.base.BaseRetrofitManager;
import com.limin.myapplication3.base.BaseTransformer;
import com.limin.myapplication3.model.UserInfoModel;
import com.limin.myapplication3.model.UserModel;

import rx.Observable;

/**
 * Description：网络请求定义类（单例模式）
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class MainSingleApi {
    private static volatile MainSingleApi instance = null;

    private MainSingleApi(){}

    public static MainSingleApi getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (MainSingleApi.class){
            // double checkout
            if(null == instance){
                instance = new MainSingleApi();
            }
        }
     }
     return instance;
    }

    /**
     * 登录
     * @param encrypt base64编码串
     * @return Observable<UserModel>
     */
    public Observable<UserModel> login(String encrypt) {
        return BaseRetrofitManager.getInstance()
                .baseService()
                .login(encrypt)
                .compose(BaseTransformer.defaultSchedulers());
    }

    /**
     * 获取用户信息
     * @return Observable<UserInfoModel>
     */
    public Observable<UserInfoModel> user() {
        return BaseRetrofitManager.getInstance()
                .getCommunityService()
                .user()
                .compose(BaseTransformer.defaultSchedulers());
    }
}
