package com.limin.myapplication3.fragment.mine;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.dragtop.DragTopLayoutActivity;
import com.limin.myapplication3.activity.swipemenu.SwipeMenuRecyclerViewActivity;
import com.limin.myapplication3.activity.videoplay.VideoPlayActivity;
import com.limin.myapplication3.activity.zoomscrollview.ZoomScrollViewActivity;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.service.BackgroundService;
import com.limin.myapplication3.utils.TitleBuilder;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29 ssssss
 */
public class MineFragment extends BaseFragment implements MineContract.View, MultiLineChooseLayout.onItemClickListener {
    @BindView(R.id.fragment_layout_folw)
    MultiLineChooseLayout fragmentLayoutFolw;

    private MineContract.Presenter presenter;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return new TitleBuilder(getActivity(), view).setMiddleTitleBgRes("我的", R.color.black, R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        presenter = new MinePresenter(this);
        presenter.start();
    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {
        fragmentLayoutFolw.setOnItemClickListener(this);
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showAddView(String[] stringArray) {
        fragmentLayoutFolw.setList(stringArray);
    }

    @Override
    public void onItemClick(int position, String text) {
        ToastUtils.showShort(text);
        switch (position) {
            case 0:
                BackgroundService.startService(getContext());
                break;
            case 1:
                SwipeMenuRecyclerViewActivity.startActivity(getActivity());
                break;
            case 2:
                VideoPlayActivity.startActivity(getActivity());
                break;
            case 3:
                BackgroundService.stopService(getContext());
                break;
            case 4:
                ZoomScrollViewActivity.startActivity(getActivity());
                break;
            case 5:
                DragTopLayoutActivity.startActivity(getActivity());
                break;
            default:
                break;
        }
    }
}
