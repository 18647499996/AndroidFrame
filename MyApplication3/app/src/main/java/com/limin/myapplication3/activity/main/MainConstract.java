package com.limin.myapplication3.activity.main;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.limin.myapplication3.model.UserModel;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public interface MainConstract {

    interface View extends BaseView<Presenter> {
        /**
         * 显示登录返回数据
         * @param userModel 登录实体类
         */
        void showUserModel(UserModel userModel);
    }

    interface Presenter extends BasePresenter {
        /**
         * 登录
         */
        void login();
    }
}
