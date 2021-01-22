package com.limin.myapplication3.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;
import com.limin.myapplication3.model.FileModel;
import com.limin.myapplication3.utils.CacheManager;

import java.io.File;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
public class FileManagerAdapter extends BaseQuickAdapter<FileModel, BaseViewHolder> {

    private Context context;

    public FileManagerAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FileModel item) {
        helper
                .setText(R.id.item_file_manager_tv_size, CacheManager.getFormatSize(Double.parseDouble(item.getmSize())))
                .setText(R.id.item_file_manager_tv_name, item.getmFilePath().substring(item.getmFilePath().lastIndexOf('/') + 1));

        ImageView imageView = helper.getView(R.id.item_file_manager_img_cover);
        switch (item.getFileType()) {
            case 1:
                imageView.setImageResource(R.drawable.img_txt);
                break;
            case 2:
                imageView.setImageResource(R.drawable.img_doc);
                break;
            case 3:
                imageView.setImageResource(R.drawable.img_pdf);
                break;
            case 4:
                imageView.setImageResource(R.drawable.img_ppt);
                break;
            case 5:
                imageView.setImageResource(R.drawable.img_xls);
                break;
            case 6:
                imageView.setImageResource(R.drawable.img_wps);
                break;
            default:
                break;
        }

        helper.getView(R.id.item_file_manager_rel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(item.getmFilePath());
                LogUtils.d("点击文件：" + file.getAbsolutePath() + "\n" + file.getName() + "\n" + file.lastModified() + " \n" + file.length());
            }
        });
    }
}
