package com.limin.myapplication3.base;

import com.limin.myapplication3.model.UserModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Description：服务器接口定义Api
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public interface BaseService {
    /**
     * 用户登录
     * @return obserable
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResult<UserModel>> login(@Field("data") String  data);


}
