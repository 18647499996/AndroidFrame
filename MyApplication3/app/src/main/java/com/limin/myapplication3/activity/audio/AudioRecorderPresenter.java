package com.limin.myapplication3.activity.audio;


import com.limin.myapplication3.base.BaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class AudioRecorderPresenter extends BaseSubscription<AudioRecorderContract.View> implements AudioRecorderContract.Presenter {


    AudioRecorderPresenter(AudioRecorderContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}