package com.limin.myapplication3.activity.login;

import android.widget.ImageView;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.limin.myapplication3.model.UserInfoModel;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/24
 */
interface LoginConstract {

    interface View extends BaseView<Presenter> {

        /**
         * 登录成功返回数据
         * @param userModel 用户信息
         */
        void showUserModel(UserInfoModel userModel);
    }

    interface Presenter extends BasePresenter {
        /**
         * 启动动画
         * @param activityLoginImgBg imageview
         */
        void startAnimation(ImageView activityLoginImgBg);

        /**
         * 登录用户
         * @param user 用户名
         * @param pws 密码
         */
        void login(String user, String pws);
    }
}
