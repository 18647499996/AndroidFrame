package com.limin.myapplication3.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.limin.myapplication3.utils.ActivityTaskManager;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Description：Activity通用管理
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 初始化布局
        setContentView(getLayout());
        // 初始化ButterKnife
        ButterKnife.bind(this);
        // 初始化数据
        initData(savedInstanceState);
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
    protected abstract void onClickListener(View v);


    @Override
    public void onClick(View v) {
        if (fastClick()) {
            onClickListener(v);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityTaskManager.getActivityManager().finishActivity(this);
    }

    /**
     * 防止快速点击（1秒响应一次）
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    public <P> P getInstance(Object o, int i) {
        try {
            return ((Class<P>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;

    }
}
