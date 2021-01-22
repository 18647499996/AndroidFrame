package com.limin.myapplication3.activity.file;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

import net.lucode.hackware.magicindicator.MagicIndicator;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
public class FileManagerActivity extends BaseActivity<FileManagerPresenter> implements FileManagerConstract.View {

    @BindView(R.id.activity_file_manager_magic_indicator)
    MagicIndicator activityFileManagerMagicIndicator;
    @BindView(R.id.activity_file_manager_vp)
    ViewPager activityFileManagerVp;

    /**
     * 启动文件管理器
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context,FileManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_file_manager;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("文件管理器");
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.start();
        mPresenter.setViewPager(this,activityFileManagerVp);
        mPresenter.setMagicIndicator(activityFileManagerMagicIndicator,activityFileManagerVp,this);
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
    protected FileManagerPresenter createPresenter() throws RuntimeException {
        return (FileManagerPresenter) new FileManagerPresenter(this).Bulider(this);
    }

    @Override
    public void setPresenter(FileManagerConstract.Presenter presenter) {
        mPresenter = (FileManagerPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
