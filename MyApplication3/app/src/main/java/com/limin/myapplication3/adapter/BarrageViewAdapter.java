package com.limin.myapplication3.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.limin.myapplication3.R;
import com.limin.myapplication3.model.BarrageModel;
import com.orient.tea.barragephoto.adapter.BarrageAdapter;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/11/18
 */
public class BarrageViewAdapter extends BarrageAdapter.BarrageViewHolder<BarrageModel> {
    private Context context;
    private TextView textView;
    public BarrageViewAdapter(View root, Context context) {
        super(root);
        this.context = context;
         textView = root.findViewById(R.id.tv_gemstone_info);
    }

    @Override
    protected void onBind(BarrageModel data) {
        textView.setText(data.getText());
    }
}
