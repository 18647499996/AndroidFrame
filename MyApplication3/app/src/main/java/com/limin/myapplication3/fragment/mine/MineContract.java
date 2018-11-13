package com.limin.myapplication3.fragment.mine;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/13
 */
interface MineContract {
    interface View extends BaseView<Presenter> {

        /**
         * 添加view
         * @param textView
         */
        void showAddView(String[] textView);
    }

    interface Presenter extends BasePresenter {

    }
}
