package com.limin.myapplication3.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;
import com.limin.myapplication3.model.DataModel;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/30
 */
public class HomeFragmentAdapter extends BaseQuickAdapter<DataModel,BaseViewHolder> {

    public HomeFragmentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataModel item) {
        helper.setImageResource(R.id.item_fragment_home_img_bg,item.getIcon());
    }
}
