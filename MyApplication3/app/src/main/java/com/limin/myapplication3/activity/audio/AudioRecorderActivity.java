package com.limin.myapplication3.activity.audio;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;
import com.limin.myapplication3.view.AudioRecorderButton;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class AudioRecorderActivity extends BaseActivity<AudioRecorderPresenter> implements AudioRecorderContract.View, AudioRecorderButton.AudioFinishRecorderListener {

    @BindView(R.id.btn)
    AudioRecorderButton audioRecorderButton;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_audio_recorder;
    }

    @Override
    protected TitleBuilder initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected AudioRecorderPresenter createPresenter() throws RuntimeException {
        return (AudioRecorderPresenter) new AudioRecorderPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        audioRecorderButton.setAudioFinishRecorderListener(this);
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(AudioRecorderContract.Presenter presenter) {
        mPresenter = (AudioRecorderPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void onFinish(int seconds, String FilePath) {
        LogUtils.d("时长：" + seconds + " ------ " + "文件路径：" + FilePath);
    }
}
