package com.limin.myapplication3.base;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.limin.myapplication3.R;
import com.limin.myapplication3.utils.ActivityTaskManager;
import com.limin.myapplication3.utils.Constant;
import com.limin.myapplication3.utils.LoadingDialogUtils;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.Calendar;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Description：Activity通用管理
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {

    public ImmersionBar immersionBar;
    private long lastClickTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // 锁定屏幕
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            // 初始化布局
            setContentView(getLayout());
            // 初始化ButterKnife
            ButterKnife.bind(this);
            // 初始化标题
            initBuilerTitle();
            // 初始化数据
            initDatas(savedInstanceState);
            // 设置监听事件
            addListener();
            // Activity管理器
            ActivityTaskManager.getActivityManager().addActivity(this);
        }catch (Exception e){
            ToastUtils.showShort("Abort,Retry, Ignore,fail！");
            e.printStackTrace();
        }

    }


    /**
     * 初始化布局
     * @return 布局文件
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract int getLayout() throws RuntimeException;

    /**
     * 初始化标题
     * @return TitleBuilder 实例
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract TitleBuilder initBuilerTitle() throws RuntimeException;

    /**
     * 初始化数据
     * @param savedInstanceState bundle属性
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract void initData(Bundle savedInstanceState) throws RuntimeException;

    /**
     *
     * 添加监听事件
     * @throws RuntimeException
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract void addListener() throws RuntimeException;

    /**
     * 设置点击事件
     * @param view view
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract void onClickDoubleListener(View view) throws RuntimeException;

    /**
     * activity销毁
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected abstract void onDestroys() throws RuntimeException;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        // 防止快速点击（1秒响应一次）
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > Constant.NO_CLICK) {
            lastClickTime = currentTime;
            onClickDoubleListener(v);
        }
    }

    /**
     * presenter 空指针异常捕获
     *
     * @param reference presenter
     * @param <T>       泛型类
     * @return presenter
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 初始化数据逻辑
     * @param savedInstanceState Bundle数据
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initDatas(Bundle savedInstanceState){
        // 初始化沉浸式
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(R.color.black).statusBarDarkFont(false).init();
        View view = findViewById(R.id.act_title_bor);
        if (null != view){
            view.setPadding(0,getStatusBarHeight(),0,0);
        }
        initData(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoadingDialogUtils.getInstance().destory();
        ActivityTaskManager.getActivityManager().finishActivity(this);
        if (null != immersionBar){
            immersionBar.destroy();
        }
        onDestroys();
    }

    /**
     * 获取状态栏高度
     * @return 状态栏高度
     */
    protected static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
