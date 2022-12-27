package com.limin.myapplication3.fragment.filemanager;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.FileManagerAdapter;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.model.FileModel;
import com.limin.myapplication3.utils.TitleBuilder;

import java.util.List;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/14
 */
public class WpsFragment extends BaseFragment<FilePresenter> implements FileConstract.View {
    @BindView(R.id.fragment_file_rv)
    RecyclerView fragmentFileRv;

    private FileManagerAdapter fileManagerAdapter;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_file;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.onSubscribe();
        fileManagerAdapter = new FileManagerAdapter(R.layout.item_file_manager,getActivity());
        fragmentFileRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentFileRv.setAdapter(fileManagerAdapter);
        mPresenter.getFileDataList(getActivity(), 6);
    }

    @Override
    protected void onClickDoubleListener(View paramView) throws RuntimeException {

    }

    @Override
    protected void setListener() throws RuntimeException {

    }

    @Override
    protected FilePresenter createPresenter() throws RuntimeException {
        return (FilePresenter) new FilePresenter(this).builder(getActivity());
    }

    @Override
    public void setPresenter(FileConstract.Presenter presenter) {
        mPresenter = (FilePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showFileModelList(List<FileModel> mDocuments) {
        fileManagerAdapter.setNewData(mDocuments);
    }
}
