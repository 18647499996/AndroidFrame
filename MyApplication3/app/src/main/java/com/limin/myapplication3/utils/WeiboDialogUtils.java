package com.limin.myapplication3.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.limin.myapplication3.R;


/**
 * @author ：Li_Min
 * 功能：风火轮对话窗
 */

public class WeiboDialogUtils {

	private Dialog loadingDialog;

	private static class WeiboDialogUtilsHolder {
		private static final WeiboDialogUtils INSTANCE = new WeiboDialogUtils();
	}
	private WeiboDialogUtils(){}
	public static WeiboDialogUtils getInstance() {
		return WeiboDialogUtilsHolder.INSTANCE;
	}


	public void createLoadingDialog(Context context, String msg) {
		LayoutInflater inflater = LayoutInflater.from(context);
		// 得到加载view
		View v = inflater.inflate(R.layout.dialog_loading, null);
		// 加载布局
		LinearLayout layout = v.findViewById(R.id.dialog_loading_view);
		// 提示文字
		TextView tipTextView = v.findViewById(R.id.tipTextView);
		// main.xml加载ImageView
		ImageView spaceshipImage = v.findViewById(R.id.img);
		spaceshipImage.setBackgroundResource(R.drawable.progress_round);
		AnimationDrawable animationDrawable = (AnimationDrawable) spaceshipImage.getBackground();
		animationDrawable.start();
		// 设置加载信息
		tipTextView.setText(msg);
		// 创建自定义样式dialog
		loadingDialog = new Dialog(context, R.style.MyDialogStyle);
		//  是否可以按“返回键”消失
		loadingDialog.setCancelable(true);
		// 点击加载框以外的区域
		loadingDialog.setCanceledOnTouchOutside(false);
        // 设置布局
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		//将显示Dialog的方法封装在这里面
		Window window = loadingDialog.getWindow();
		WindowManager.LayoutParams lp = window != null ? window.getAttributes() : null;
		if (lp != null) {
			lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
			window.setGravity(Gravity.CENTER);
			window.setAttributes(lp);
			window.setWindowAnimations(R.style.PopWindowAnimStyle);
		}
		loadingDialog.show();

	}

	/**
     * 关闭dialog
     *
     * http://blog.csdn.net/qq_21376985
     *
     */
	public void closeDialog() {
		if (loadingDialog != null && loadingDialog.isShowing()) {
			loadingDialog.dismiss();
		}
	}

}