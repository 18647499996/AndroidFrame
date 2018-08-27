package com.limin.myapplication3.activity.main;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.api.MainSingleApi;
import com.limin.myapplication3.base.BaseException;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseRequestResult;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.Constant;
import com.limin.myapplication3.utils.EncryptMap;
import com.limin.myapplication3.utils.rx.Event;
import com.limin.myapplication3.utils.rx.EventSubscriber;
import com.limin.myapplication3.utils.rx.RxBus;

import rx.Observable;
import rx.Subscription;

/**
 * Description： 逻辑处理数据交互（Presenter层）
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class MainPresenter extends BaseSubscription implements MainConstract.Presenter {

    private MainConstract.View view;
    private Observable<Event> register;

    MainPresenter(MainConstract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        register = RxBus.get().register(Constant.TOKEN, new EventSubscriber<Event>() {
            @Override
            public void onEvent(Event event) {
                LogUtils.d("所有服务器-7操作都执行登录页面跳转");
                view.showOutLogin();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(Constant.TOKEN,register);
    }

    @Override
    public void login() {
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
