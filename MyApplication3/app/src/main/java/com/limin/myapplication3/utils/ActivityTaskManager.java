package com.limin.myapplication3.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import androidx.annotation.Nullable;

import com.limin.myapplication3.activity.main.MainActivity;
import com.limin.myapplication3.app.App;

import java.util.Stack;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class ActivityTaskManager {

	private static Stack<Activity> activityStack;
	private static ActivityTaskManager instance;

	private ActivityTaskManager() {
		activityStack = new Stack<>();
	}

	/**
	 * 单一实例
	 */
	public static ActivityTaskManager getActivityManager() {
		if (instance == null) {
			instance = new ActivityTaskManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(@Nullable Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishCurrentActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(@Nullable Activity activity) {
		if(activity != null && !activity.isFinishing()) {
			activity.finish();

		}
		if (activityStack !=null && activityStack.size() >0 && activityStack.contains(activity)) {
			activityStack.remove(activity);
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(@Nullable Class<?> cls) {
		for (int i = 0;i < activityStack.size(); i++){
			Activity activity = activityStack.get(i);
			if (activity != null && activity.getClass().equals(cls)) {
				activityStack.remove(activity);
				if(!activity.isFinishing()){
					activity.finish();
				}
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				if(!activityStack.get(i).isFinishing()){
					activityStack.get(i).finish();
				}
			}
		}
		activityStack.clear();
	}

	/**
	 * 遍历所有Activity并finish
	 * @param isExit true关闭所有activity false关闭部分activity(一般被挤下线后传false)
	 */
	public void exit(boolean isExit) {
		if (isExit) {
			ActivityManager manager = (ActivityManager) App.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
			manager.killBackgroundProcesses(App.getInstance().getPackageName());

			finishAllActivity();

			System.exit(0);
		} else {
			finishAllActivity();
		}
	}

	/**
	 * 移除首页上面数据
	 */
	public void goBackToHome(){
		while(!activityStack.isEmpty() &&!(activityStack.peek() instanceof MainActivity)){
			activityStack.pop().finish();
		}
	}

	/**
	 * 移除指定页面上面数据
	 * @param c
	 */
	public void goBackTo(Class c){
		while(!activityStack.isEmpty() &&!(activityStack.peek().getClass() == c)){
			activityStack.pop().finish();
		}
	}

	/**
	 * 是否添加首页
	 */
	public boolean isGoBackToHome(){
		for (int i = 0; i < activityStack.size(); i++) {
			if (activityStack.get(i).getClass() == MainActivity.class) {
				return true;
			}
		}
		return false;
	}
}
