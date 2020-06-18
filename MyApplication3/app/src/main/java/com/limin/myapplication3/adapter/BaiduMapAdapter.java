package com.limin.myapplication3.adapter;

import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
public class BaiduMapAdapter extends BaseQuickAdapter<PoiItem, BaseViewHolder> {

    public BaiduMapAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        helper.setText(R.id.item_baidu_map_tv_name, item.getTitle())
                .setText(R.id.item_baidu_map_tv_address, item.getCityName() + item.getAdName() + item.getSnippet());
    }
}
