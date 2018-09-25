package com.limin.myapplication3.api;

import com.limin.myapplication3.base.BaseRetrofitManager;
import com.limin.myapplication3.base.BaseTransformer;
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
     *
     * @param encrypt
     * @return
     */
    public Observable<UserModel> login(String encrypt) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOnsidXNlcmlkIjo0Mn0sInN1YiI6IjE4NjQ3NDk5OTk2IiwiZXhwIjoxNTM3NzczMzk3fQ.TWF44idsjbRsDNS67nOOpXayxoje3z8BNipOfHGSnkw";
        String mobile = "18647499996";
        String type = "2";
        return BaseRetrofitManager.getInstance()
                .baseService()
                .login(token,mobile,type)
                .compose(BaseTransformer.defaultSchedulers());
    }

}
