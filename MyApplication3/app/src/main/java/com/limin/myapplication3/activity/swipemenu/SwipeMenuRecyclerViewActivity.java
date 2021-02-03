package com.limin.myapplication3.activity.swipemenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.IssueAdapter;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.model.StaggeredModel;
import com.limin.myapplication3.utils.ListDataUtils;
import com.limin.myapplication3.utils.TitleBuilder;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * Description：侧滑菜单
 *
 * @author Created by: Li_Min
 * Time:2018/11/15
 */
public class SwipeMenuRecyclerViewActivity extends BaseActivity<SwipeMenuRecyclerViewPrestener> implements SwipeMenuRecyclerViewConstract.View, SwipeMenuCreator, SwipeMenuItemClickListener {
    @BindView(R.id.activity_swipemenu_rv)
    SwipeMenuRecyclerView activitySwipemenuRv;

    private List<StaggeredModel> staggeredModelList = ListDataUtils.staggeredDataList();
    private SwipeMenuRecyclerViewConstract.Presenter presenter;

    /**
     * 启动侧滑菜单页面
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SwipeMenuRecyclerViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_swipemenu_recyclerview;
    }

    @Override
    protected TitleBuilder initBuilderTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("侧滑菜单", R.color.black, R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        presenter.onSubscribe();
        IssueAdapter issueAdapter = new IssueAdapter(R.layout.item_issue, this);
        activitySwipemenuRv.setSwipeMenuCreator(this);
        activitySwipemenuRv.setSwipeMenuItemClickListener(this);
        activitySwipemenuRv.setAdapter(issueAdapter);
        activitySwipemenuRv.setLayoutManager(new LinearLayoutManager(this));
        issueAdapter.setNewData(staggeredModelList);
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
    protected SwipeMenuRecyclerViewPrestener createPresenter() throws RuntimeException {
        return (SwipeMenuRecyclerViewPrestener) new SwipeMenuRecyclerViewPrestener(this).builder(this);
    }

    @Override
    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
        SwipeMenuItem deleteItem = new SwipeMenuItem(this)
                // 背景颜色
                .setBackgroundColor(getResources().getColor(R.color.colorAccent))
                // 文字。
                .setText("删除")
                // 文字颜色。
                .setTextColor(Color.WHITE)
                // 文字大小。
                .setTextSize(16)
                // 宽
                .setWidth(200)
                //高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用））
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 添加一个按钮到右侧侧菜单。
        swipeRightMenu.addMenuItem(deleteItem);
    }

    @Override
    public void onItemClick(SwipeMenuBridge menuBridge) {
        // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
        menuBridge.closeMenu();
        //在menuBridge中我们可以得到侧滑的这一项item的position (menuBridge.getAdapterPosition())
        int position = menuBridge.getAdapterPosition();
        ToastUtils.showShort("点击：" + position);
    }

    @Override
    public void setPresenter(SwipeMenuRecyclerViewConstract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
