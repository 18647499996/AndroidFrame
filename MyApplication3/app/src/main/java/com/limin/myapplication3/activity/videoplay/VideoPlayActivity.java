package com.limin.myapplication3.activity.videoplay;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/11/20
 */
public class VideoPlayActivity extends BaseActivity {

    @BindView(R.id.activity_videoplay_vv)
    VideoView activityVideoplayVv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_videoplay;
    }

    @Override
    protected TitleBuilder initBuilderTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("视频播放",R.color.black,R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
//        activityVideoplayVv.setVideoPath("http://yun.it7090.com/video/XHLaunchAd/video01.mp4");
        MediaController mc = new MediaController(this);
        mc.setAnchorView(activityVideoplayVv);
        mc.setMediaPlayer(activityVideoplayVv);
        activityVideoplayVv.setMediaController(mc);
        String _path = "http://www.youtube.com/watch?v=E43mgXNl0xc";
        Uri uri=Uri.parse(_path);
        activityVideoplayVv.setVideoURI(uri);
        //videoView.setVideoPath(_path);

        activityVideoplayVv.requestFocus();
        activityVideoplayVv.start();
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
    protected BasePresenter createPresenter() throws RuntimeException {
        return null;
    }
}
