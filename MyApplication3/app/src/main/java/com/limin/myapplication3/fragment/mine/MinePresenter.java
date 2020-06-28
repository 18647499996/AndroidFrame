package com.limin.myapplication3.fragment.mine;

import com.limin.myapplication3.api.MainSingleApi;
import com.limin.myapplication3.base.BaseResult;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.interfaces.UserService;
import com.limin.myapplication3.model.UserInfoModel;
import com.limin.myapplication3.model.UserModel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.observables.ConnectableObservable;
import rx.observables.SyncOnSubscribe;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/13
 */
class MinePresenter extends BaseSubscription<MineContract.View> implements MineContract.Presenter {

    private String[] stringArray = new String[]{"开启服务","侧滑删除","视频播放器","停止服务","CollapsingToolbarLayout","Top下拉布局","NewIntent","加密指纹","解密指纹","高德定位","停止定位","搞得地图"};

    MinePresenter(MineContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        view.showAddView(stringArray);

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void retrofitOrigin() {
        
    }
}
