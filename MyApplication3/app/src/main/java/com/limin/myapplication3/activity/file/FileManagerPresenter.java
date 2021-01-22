package com.limin.myapplication3.activity.file;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.limin.myapplication3.base.BasePageAdapter;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.fragment.filemanager.DocFragment;
import com.limin.myapplication3.fragment.filemanager.PdfFragment;
import com.limin.myapplication3.fragment.filemanager.PptFragment;
import com.limin.myapplication3.fragment.filemanager.TxtFragment;
import com.limin.myapplication3.fragment.filemanager.WpsFragment;
import com.limin.myapplication3.fragment.filemanager.XlsFragment;
import com.limin.myapplication3.utils.MagicIndicatorUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
class FileManagerPresenter extends BaseSubscription<FileManagerConstract.View> implements FileManagerConstract.Presenter {

    private MagicIndicatorUtils magicIndicatorUtils;

    protected FileManagerPresenter(FileManagerConstract.View view) {
        super(view);
        magicIndicatorUtils = MagicIndicatorUtils.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void setViewPager(FileManagerActivity fileManagerActivity, ViewPager activityFileManagerVp) {
        //分别对应 txt doc pdf ppt xls wps docx pptx xlsx 类型的文档
        List<Fragment> fragmentList = new ArrayList<>();
        TxtFragment txtFragment = new TxtFragment();
        DocFragment docFragment = new DocFragment();
        PdfFragment pdfFragment = new PdfFragment();
        PptFragment pptFragment = new PptFragment();
        XlsFragment xlsFragment = new XlsFragment();
        WpsFragment wpsFragment = new WpsFragment();

        fragmentList.add(txtFragment);
        fragmentList.add(docFragment);
        fragmentList.add(pdfFragment);
        fragmentList.add(pptFragment);
        fragmentList.add(xlsFragment);
        fragmentList.add(wpsFragment);
        activityFileManagerVp.setAdapter(new BasePageAdapter(fileManagerActivity.getSupportFragmentManager(), fragmentList, getContext()));
        activityFileManagerVp.setOffscreenPageLimit(fragmentList.size() - 1);
    }

    @Override
    public void setMagicIndicator(MagicIndicator activityFileManagerMagicIndicator, ViewPager activityFileManagerVp, FileManagerActivity fileManagerActivity) {
        magicIndicatorUtils.
                initMagicIndicatorDefaultOption(
                        fileManagerActivity,
                        new String[]{"txt", "doc","PDF","PPT","xls","wps"},
                        activityFileManagerMagicIndicator,
                        activityFileManagerVp,
                        (index, totalCount) -> {

                        });
    }
}
