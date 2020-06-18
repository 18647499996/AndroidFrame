package com.limin.myapplication3.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.aip.nlp.AipNlp;
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

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class HomeFragment extends BaseFragment implements OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {
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
        homeFragmentAdapter.openLoadAnimation();
        fragmentHomeRv.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setInitialPrefetchItemCount(4);
        fragmentHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentHomeRv.setAdapter(homeFragmentAdapter);
        homeFragmentAdapter.replaceData(integerList);
    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {
        activityHomeSmartrefresh.setOnRefreshLoadMoreListener(this);
        homeFragmentAdapter.setOnItemChildClickListener(this);
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
        }, 3000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.getLayout().postDelayed(() -> {
            homeFragmentAdapter.replaceData(integerList);
            refreshLayout.finishRefresh();
        }, 3000);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        DataModel dataModel = (DataModel) adapter.getItem(position);
        switch (view.getId()) {
            case R.id.item_fragment_home_img_bg:
                if (!dataModel.isSeletor()){
                    dataModel.setSeletor(true);
                }else{
                    dataModel.setSeletor(false);
                }
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }
}
