package com.limin.myapplication3.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;
import com.limin.myapplication3.model.StaggeredModel;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/9/12
 */
public class IssueAdapter extends BaseQuickAdapter<StaggeredModel, BaseViewHolder> {

    private Context context;

    public IssueAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, StaggeredModel item) {
        helper.setText(R.id.item_fragment_issue_tv_name, item.getName())
                .setImageResource(R.id.item_fragment_issue_img_icon, item.getIcon());
    }
}
