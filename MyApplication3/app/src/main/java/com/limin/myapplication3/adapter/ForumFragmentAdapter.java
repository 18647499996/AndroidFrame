package com.limin.myapplication3.adapter;

import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.limin.myapplication3.R;
import com.limin.myapplication3.model.DataModel;

import java.util.List;

/**
 * Description：adapter适配器多布局引用继承自：BaseMultiItemQuickAdapter
 *
 * @author Created by: Li_Min
 * Time:2018/8/30
 */
public class ForumFragmentAdapter extends BaseMultiItemQuickAdapter<DataModel, BaseViewHolder> {


    private static final int ONE = 1001;
    private static final int TWO = 1002;
    private static final int THREE = 1003;
    private static final int FOUR = 1004;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ForumFragmentAdapter(List<DataModel> data) {
        super(data);
        addItemType(ONE, R.layout.item_fragment_home);
        addItemType(TWO, R.layout.item_fragment_home_two);
        addItemType(THREE, R.layout.item_fragment_home);
        addItemType(FOUR,R.layout.item_fragment_home_two);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataModel item) {
        // BaseViewHolder 模板设计思想 构建item当中所有的组件id，设置组件属性
        switch (helper.getItemViewType()) {
            case ONE:
                LogUtils.e("第一套布局");
                helper.setImageResource(R.id.item_fragment_home_img_bg,R.drawable.bg_machine_night).setText(R.id.item_fragment_home_tv_title,item.getName()).addOnClickListener(R.id.item_fragment_home_tv_title);
                break;
            case TWO:
                LogUtils.e("第二套布局");
                helper.setImageResource(R.id.item_fragment_home_img_bg,R.drawable.bg_machine)
                        .setText(R.id.item_fragment_home_tv_title,item.getName());
                break;
            case THREE:
                LogUtils.e("第三套布局");
                helper.setImageResource(R.id.item_fragment_home_img_bg,R.drawable.bg_machine_type1)
                        .setText(R.id.item_fragment_home_tv_title,item.getName());
                break;
            case FOUR:
                LogUtils.e("第四套布局");
                helper.setImageResource(R.id.item_fragment_home_img_bg,R.drawable.bg_machine_type1)
                        .setText(R.id.item_fragment_home_tv_title,item.getName());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        DataModel dataModel = getItem(position);
        switch (dataModel.getType()) {
            case 0:
                return ONE;
            case 1:
                return TWO;
            case 2:
                return THREE;
            case 3:
                return FOUR;
            default:
                break;
        }
        return super.getItemViewType(position);
    }
}
