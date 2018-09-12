package com.limin.myapplication3.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.limin.myapplication3.R;
import com.limin.myapplication3.utils.Constant;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 *
 * @author liudonghan 2015-11-29
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
    private Unbinder unbinder;
    private View view;
    private long lastClickTime;
    protected ImmersionBar immersionBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = initView(inflater, container, savedInstanceState);
        } else {
            container = (ViewGroup) view.getParent();
        }
        if (container != null) {
            container.removeView(view);
        }
        return view;

    }

    /**
     * 初始化布局
     */
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            View pView = inflater.inflate(loadViewLayout(), container, false);
            unbinder = ButterKnife.bind(this, pView);
            initBuilerTitle(pView);
            initDatas(savedInstanceState, pView);
            setListener();
            return pView;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 加载布局
     *
     * @return 布局文件
     */
    protected abstract int loadViewLayout();

    /**
     * 初始化标题
     *
     * @param view 布局View
     * @return TitleBuilder 实例
     */
    protected abstract TitleBuilder initBuilerTitle(View view);

    /**
     * 初始化数据
     *
     * @param savedInstanceState 初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 处理点击事件
     *
     * @param paramView 发生点击事件的组件
     */
    protected abstract void onClickDoubleListener(View paramView);

    /**
     * 设置监听器
     */
    protected abstract void setListener();


    @Override
    public void onClick(View v) {
        // 防止快速点击（1秒响应一次）
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > Constant.NO_CLICK) {
            lastClickTime = currentTime;
            onClickDoubleListener(v);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != immersionBar) {
            immersionBar.destroy();
        }
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * 初始化数据（沉浸式状态栏）
     *
     * @param savedInstanceState intent数据
     * @param pView              view视图
     */
    public void initDatas(Bundle savedInstanceState, View pView) {
        // 初始化沉浸式
        immersionBar = ImmersionBar.with(this);
        View view = pView.findViewById(R.id.act_title_bor);
        if (null != view) {
            view.setPadding(0, getStatusBarHeight(), 0, 0);
        }
        initData(savedInstanceState);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            LogUtils.d(this + "fragment可见");
        } else {
            LogUtils.d(this + "fragment不可见");
        }

    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
