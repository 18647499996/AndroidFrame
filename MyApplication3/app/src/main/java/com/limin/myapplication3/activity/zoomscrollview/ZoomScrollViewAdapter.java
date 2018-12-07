package com.limin.myapplication3.activity.zoomscrollview;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;
import com.limin.myapplication3.model.VitaeModel;

/**
 * Description：个人履历适配器
 *
 * @author Created by: Li_Min
 * Time:2018/12/4
 */
class ZoomScrollViewAdapter extends BaseQuickAdapter<VitaeModel,BaseViewHolder> {


    ZoomScrollViewAdapter(int layoutResId, Context context) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VitaeModel item) {
        helper.setText(R.id.item_vitae_tv_name,item.getName());
    }
}
