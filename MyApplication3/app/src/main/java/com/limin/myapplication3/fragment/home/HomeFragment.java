package com.limin.myapplication3.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.HomeFragmentAdapter;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.model.DataModel;
import com.limin.myapplication3.refresh.SmartRefreshLayout;
import com.limin.myapplication3.refresh.api.RefreshLayout;
import com.limin.myapplication3.refresh.listener.OnRefreshLoadMoreListener;
import com.limin.myapplication3.utils.ListDataUtils;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class HomeFragment extends BaseFragment implements OnRefreshLoadMoreListener {
    @BindView(R.id.fragment_home_rv)
    RecyclerView fragmentHomeRv;
    @BindView(R.id.activity_home_smartrefresh)
    SmartRefreshLayout activityHomeSmartrefresh;

    private List<DataModel> integerList = ListDataUtils.iconDataList();
    private HomeFragmentAdapter homeFragmentAdapter;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return new TitleBuilder(getActivity(), view).setMiddleTitleBgRes("首页", R.color.black, R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        homeFragmentAdapter = new HomeFragmentAdapter(R.layout.item_fragment_home);
        homeFragmentAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        fragmentHomeRv.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        fragmentHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentHomeRv.setAdapter(homeFragmentAdapter);
        homeFragmentAdapter.setNewData(integerList);
    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {
        activityHomeSmartrefresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.getLayout().postDelayed(() -> {
            homeFragmentAdapter.addData(integerList);
            refreshLayout.finishLoadMoreWithNoMoreData();
        },3000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.getLayout().postDelayed(() -> {
            homeFragmentAdapter.replaceData(integerList);
            refreshLayout.finishRefresh();
        },3000);
    }
}
