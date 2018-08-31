package com.limin.myapplication3.fragment.forum;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.ForumFragmentAdapter;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.model.DataModel;
import com.limin.myapplication3.refresh.SmartRefreshLayout;
import com.limin.myapplication3.refresh.api.RefreshLayout;
import com.limin.myapplication3.refresh.listener.OnRefreshLoadMoreListener;
import com.limin.myapplication3.utils.ListDataUtils;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class ForumFragment extends BaseFragment implements OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.fragment_forum_rv)
    RecyclerView fragmentForumRv;
    @BindView(R.id.activity_forum_smartrefresh)
    SmartRefreshLayout activityForumSmartrefresh;

    private List<DataModel> integerList = ListDataUtils.iconDataList();
    private ForumFragmentAdapter forumFragmentAdapter;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return new TitleBuilder(getActivity(), view).setMiddleTitleBgRes("论坛", R.color.with, R.color.black);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        // 初始化适配器
        forumFragmentAdapter = new ForumFragmentAdapter(integerList);
        // 设置分割线
        fragmentForumRv.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), VERTICAL));
        // 设置RecyclerView样式
        fragmentForumRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 添加RecyclerView加载动画
        forumFragmentAdapter.openLoadAnimation();
        // 添加适配器
        fragmentForumRv.setAdapter(forumFragmentAdapter);
        // 添加数据
        forumFragmentAdapter.setNewData(integerList);
    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {
        activityForumSmartrefresh.setOnRefreshLoadMoreListener(this);
        forumFragmentAdapter.setOnItemChildClickListener(this);
        forumFragmentAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        forumFragmentAdapter.addData(integerList);
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.getLayout().postDelayed(() -> {
            forumFragmentAdapter.replaceData(integerList);
            refreshLayout.finishRefresh();
        }, 3000);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_fragment_home_tv_title:
                ToastUtils.showShort("点击标题" + position);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DataModel item = forumFragmentAdapter.getItem(position);
        ToastUtils.showShort(item.getName()+ "：" + position);
//        TestActivity.startActivity(getActivity());
    }
}
