package com.limin.myapplication3.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.limin.myapplication3.R;
import com.limin.myapplication3.utils.ActivityTaskManager;
import com.limin.myapplication3.utils.Constant;
import com.limin.myapplication3.utils.LoadingDialogUtils;
import com.limin.myapplication3.utils.TitleBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：Activity通用管理
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {

    protected ImmersionBar immersionBar;
    private long lastClickTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
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
    }


    /**
     * 初始化布局
     *
     * @return 布局文件
     */
    protected abstract int getLayout();

    /**
     * 初始化标题
     */
    protected abstract TitleBuilder initBuilerTitle();

    /**
     * 初始化数据
     *
     * @param savedInstanceState bundle属性
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 添加监听事件
     */
    protected abstract void addListener();

    /**
     * 设置点击事件
     *
     * @param v view
     */
    protected abstract void onClickDoubleListener(View v);





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
     * @return
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
    public void initDatas(Bundle savedInstanceState){
        // 初始化沉浸式
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(R.color.black).statusBarDarkFont(false).init();
        View view = findViewById(R.id.act_title_bor);
        if (null != view){
            view.setPadding(0,getStatusBarHeight(this),0,0);
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoadingDialogUtils.getInstance().destory();
        ActivityTaskManager.getActivityManager().finishActivity(this);
        if (null != immersionBar){
            immersionBar.destroy();

        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
