package com.limin.myapplication3.interfaces;

import com.limin.myapplication3.base.BaseResult;
import com.limin.myapplication3.model.UserInfoModel;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/25
 */
public interface CommunityService {

    /**
     * 获取用户信息
     * @return observble
     */
    @POST("api/v2/index/public?")
    Observable<BaseResult<UserInfoModel>> user();
}
