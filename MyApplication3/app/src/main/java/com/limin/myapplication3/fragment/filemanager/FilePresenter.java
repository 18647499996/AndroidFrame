package com.limin.myapplication3.fragment.filemanager;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.base.BaseSubscription;
import com.limin.myapplication3.model.FileModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
class FilePresenter extends BaseSubscription<FileConstract.View> implements FileConstract.Presenter {

    private List<FileModel> mDocuments = new ArrayList<>();

    protected FilePresenter(FileConstract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void getFileDataList(FragmentActivity activity, int fileType) {
        ContentResolver contentResolver = activity.getContentResolver();
        String[] projection = new String[]{
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.TITLE, MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_MODIFIED};
        //分别对应 txt doc pdf ppt xls wps docx pptx xlsx 类型的文档
        String selection = null;
        String[] selectionArgs = new String[0];

        switch (fileType) {
            case 1:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";
                selectionArgs = new String[]{"text/plain"};
                break;
            case 2:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";
                selectionArgs = new String[]{"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
                break;
            case 3:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";
                selectionArgs = new String[]{"application/pdf"};
                break;
            case 4:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";
                selectionArgs = new String[]{"application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};
                break;
            case 5:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?" + " or " + MediaStore.Files.FileColumns.MIME_TYPE + " = ? ";
                selectionArgs = new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
                break;
            case 6:
                selection = MediaStore.Files.FileColumns.MIME_TYPE + "= ?";
                selectionArgs = new String[]{"application/vnd.ms-works"};
                break;
            default:
                break;
        }
        Cursor cursor = contentResolver.query(MediaStore.Files.getContentUri("external"), projection,
                selection, selectionArgs, MediaStore.Files.FileColumns.DATE_MODIFIED + " desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                String size = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE));
                String date = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED));
                if (scannerFile(filePath)) {
                    FileModel fileItem = new FileModel(filePath, null, size, date, 0, fileType);
                    mDocuments.add(fileItem);
                }
            }
            LogUtils.d("文件列表：" + mDocuments.toString());
            cursor.close();
            view.showFileModelList(mDocuments);
        }
    }

    private boolean scannerFile(String path) {
        File file = new File(path);
        return file.exists() && file.length() > 0;
    }
}
