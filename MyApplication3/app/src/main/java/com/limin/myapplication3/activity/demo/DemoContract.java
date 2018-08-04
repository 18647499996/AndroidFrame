package com.limin.myapplication3.activity.demo;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.limin.myapplication3.model.UserModel;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
interface DemoContract {
    interface View extends BaseView<Presenter> {
        /**
         * 登录返回数据
         * @param userModel 用户实体
         */
        void showUserModel(UserModel userModel);
    }

    interface Presenter extends BasePresenter {

        void demo();
    }
}
