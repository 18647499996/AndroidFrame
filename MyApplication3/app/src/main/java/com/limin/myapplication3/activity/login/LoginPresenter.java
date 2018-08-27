package com.limin.myapplication3.activity.login;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.api.MainSingleApi;
import com.limin.myapplication3.base.BaseException;
import com.limin.myapplication3.base.BaseRequestResult;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.utils.EncryptMap;

import java.util.Random;

import rx.Subscription;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/24
 */
class LoginPresenter extends BaseSubscription implements LoginConstract.Presenter {

    private LoginConstract.View view;
    private int[] b = {R.drawable.bg_machine, R.drawable.bg_machine_night, R.drawable.bg_machine_noon, R.drawable.bg_machine_type1};
    private MainSingleApi mainSingleApi;


    LoginPresenter(LoginConstract.View view) {
        this.view = view;
        this.mainSingleApi = MainSingleApi.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void startAnimation(ImageView activityLoginImgBg) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f,
                AlphaAnimation.RELATIVE_TO_SELF, 0.5f, AlphaAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        activityLoginImgBg.setAnimation(scaleAnimation);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activityLoginImgBg.setImageResource(R.drawable.bg_machine);
                ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.5f, 1.0f, 1.5f, 1.0f,
                        AlphaAnimation.RELATIVE_TO_SELF, 0.5f, AlphaAnimation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation2.setDuration(3000);
                activityLoginImgBg.startAnimation(scaleAnimation2);
                scaleAnimation2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Random rand = new Random();
                        int num = rand.nextInt(3);
                        activityLoginImgBg.setImageResource(b[num]);
                        activityLoginImgBg.startAnimation(scaleAnimation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void login(String user, String pws) {
        EncryptMap map = new EncryptMap();
        map.put("mobile", user);
        map.put("password", pws);
        map.put("client", 2);
        map.put("loginType", 1);
        Subscription subscribe = mainSingleApi.login(map.encrypt()).subscribe(new BaseRequestResult<UserModel>(getContext()) {
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
