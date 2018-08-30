package com.limin.myapplication3.fragment.issue;

import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.utils.TitleBuilder;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29
 */
public class IssueFragment extends BaseFragment {
    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_issue;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {

    }
}
