package com.limin.myapplication3.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.limin.myapplication3.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/24/22
 */
public class AudioDialogManage {
    private Dialog mDialog;
    private ImageView dialogVoiceManageImgRun, dialogVoiceManageImgCancel;
    //录音提示文字
    private TextView dialogVoiceManageTvHint;
    private Context mContext;


    public AudioDialogManage(Context context) {
        this.mContext = context;
    }

    /**
     * 默认的对话框的显示
     */
    public void showRecorderingDialog() {
        mDialog = new Dialog(mContext, R.style.Theme_AudioDialog);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.dialog_voice_manage, null);
        mDialog.setContentView(view);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        dialogVoiceManageImgRun = (ImageView) mDialog.findViewById(R.id.dialog_voice_manage_img_run);
        dialogVoiceManageTvHint = (TextView) mDialog.findViewById(R.id.dialog_voice_manage_tv_hint);
        dialogVoiceManageImgCancel = mDialog.findViewById(R.id.dialog_voice_manage_img_cancel);
        Glide.with(mContext).load(R.drawable.icon_voice_bg).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(dialogVoiceManageImgRun);
        mDialog.show();
    }

    //下面在显示各种对话框时，mDialog已经被构造，只需要控制ImageView、TextView的显示即可

    /**
     * 正在录音时，Dialog的显示
     */
    public void recording() {
        if (mDialog != null && mDialog.isShowing()) {
            dialogVoiceManageImgCancel.setVisibility(View.INVISIBLE);
            dialogVoiceManageImgRun.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(R.drawable.icon_voice_bg).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(dialogVoiceManageImgRun);
            dialogVoiceManageTvHint.setBackgroundColor(Color.parseColor("#00000000"));
            dialogVoiceManageTvHint.setText("手指上划，取消发送");
        }
    }

    /**
     * 取消录音提示对话框
     */
    public void wantToCancel() {
        if (mDialog != null && mDialog.isShowing()) {
            dialogVoiceManageImgCancel.setVisibility(View.VISIBLE);
            dialogVoiceManageImgRun.setVisibility(View.INVISIBLE);
            dialogVoiceManageTvHint.setBackground(mContext.getResources().getDrawable(R.drawable.corners_voice_cancel_tv_bg));
            dialogVoiceManageTvHint.setText("松开手指，取消发送");
        }
    }

    /**
     * 录音时间过短
     */
    public void tooShort() {
        if (mDialog != null && mDialog.isShowing()) {
            dialogVoiceManageImgCancel.setVisibility(View.VISIBLE);
            dialogVoiceManageImgRun.setVisibility(View.INVISIBLE);
            dialogVoiceManageTvHint.setBackground(mContext.getResources().getDrawable(R.drawable.corners_voice_cancel_tv_bg));
            dialogVoiceManageTvHint.setText("时间太短，发送失败");
        }
    }

    /**
     * mDialog.dismiss();
     */
    public void dismissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    /**
     * 更新显示当前录音秒数
     *
     * @param time 录音时长
     */
    public void updateCurTime(String time) {
        if (mDialog != null && mDialog.isShowing()) {
            // 设置录音时长
        }
    }
}
