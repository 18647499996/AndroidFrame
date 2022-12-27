package com.limin.myapplication3.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.utils.HanlderUtils;

/**
 * Description：推送服务
 *
 * @author Created by: Li_Min
 * Time:2018/11/11
 */
public class BackgroundService extends Service {

    public static void startService(Context context) {
        Intent intent = new Intent(context, BackgroundService.class);
        context.startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

    }

    private void init() {
        HanlderUtils.getInstance().delayExecute(() -> {
            init();
            LogUtils.d("开启服务");
        }, HanlderUtils.MILLIS);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, BackgroundService.class);
        context.stopService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HanlderUtils.getInstance().clearHanlder();
        stopPlayMusic();
        LogUtils.d("停止服务--------------------");
    }


    private final String TAG = "MusicService";

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    LogUtils.d(TAG, "AUDIOFOCUS_GAIN");
                    try {
                        startPlayMusic();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    LogUtils.e(TAG, "AUDIOFOCUS_LOSS");
                    mAudioManager.abandonAudioFocus(mAudioFocusChange);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    LogUtils.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    LogUtils.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //音标处理
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (mAudioManager != null) {
            mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        }

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bg);
        mMediaPlayer.setLooping(true);

        startPlayMusic();
        init();
        return START_STICKY;
    }


    private void startPlayMusic() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            LogUtils.e(TAG, "启动后台播放音乐");
            mMediaPlayer.start();
        }
    }

    private void stopPlayMusic() {
        if (mMediaPlayer != null) {
            LogUtils.e(TAG, "关闭后台播放音乐");
            mMediaPlayer.stop();
        }
    }
}
