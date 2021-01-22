package com.limin.myapplication3.fragment.filemanager;

import android.support.v4.app.FragmentActivity;

import com.limin.myapplication3.base.BasePresenter;
import com.limin.myapplication3.base.BaseView;
import com.limin.myapplication3.model.FileModel;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
interface FileConstract {
    interface View extends BaseView<Presenter> {

        /**
         * 显示文件列表
         * @param mDocuments 文件列表
         */
        void showFileModelList(List<FileModel> mDocuments);
    }

    interface Presenter extends BasePresenter {

        /**
         * 获取文件列表
         * @param activity activity引用
         * @param fileType 文件类型
         */
        void getFileDataList(FragmentActivity activity, int fileType);
    }
}
