package com.limin.myapplication3.activity.demo;

import com.limin.myapplication3.api.MainSingleApi;
import com.limin.myapplication3.base.BaseException;
import com.limin.myapplication3.base.BaseRequestResult;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.EncryptMap;

import rx.Subscription;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
class DemoPresenter extends BaseSubscription implements DemoContract.Presenter {

    private DemoContract.View view;

    public DemoPresenter(DemoContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void demo() {
        EncryptMap map = new EncryptMap();
        map.put("mobile", "18647499996");
        map.put("password", "123456");
        map.put("client", 2);
        map.put("loginType", 1);
        Subscription subscribe = MainSingleApi
                .getInstance()
                .login(map.encrypt())
                .subscribe(new BaseRequestResult<UserModel>(getContext()) {
                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(BaseException.ApiException e) {
                        view.showErrorMessage(e.getDisplayMessage());
                    }

                    @Override
                    protected void onNextListener(UserModel userModel) {
                        view.showUserModel(userModel);
                    }
                });
        subscriptions.add(subscribe);
    }

}
