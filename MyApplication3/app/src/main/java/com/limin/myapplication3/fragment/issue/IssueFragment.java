package com.limin.myapplication3.fragment.issue;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.IssueAdapter;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.model.StaggeredModel;
import com.limin.myapplication3.utils.ListDataUtils;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class IssueFragment extends BaseFragment {
    @BindView(R.id.fragment_issue_rx)
    RecyclerView fragmentIssueRx;

    private List<StaggeredModel> staggeredModelList = ListDataUtils.staggeredDataList();
    private IssueAdapter issueAdapter;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_issue;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return new TitleBuilder(getActivity(),view).setMiddleTitleBgRes("瀑布流布局",R.color.black,R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        issueAdapter = new IssueAdapter(R.layout.item_issue,getActivity());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        fragmentIssueRx.setLayoutManager(layoutManager);
        fragmentIssueRx.setAdapter(issueAdapter);
        issueAdapter.setNewData(staggeredModelList);
    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {

    }

}
