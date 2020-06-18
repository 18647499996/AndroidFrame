package com.limin.myapplication3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2018/12/21
 */
public class ActivityB extends BaseActivity {


    @BindView(R.id.activity_b_tv)
    TextView activityBTv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ActivityB.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_b;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        LogUtils.d("------------onCreate--------------BBBBBBBBBBB");
    }

    @Override
    protected void addListener() throws RuntimeException {
        activityBTv.setOnClickListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {
        switch (view.getId()) {
            case R.id.activity_b_tv:
                activityBTv.setText("ActivityB");
                Intent intent = new Intent(this, ActivityA.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {
        LogUtils.d("------------onDestroys--------------BBBBBBBBBBB");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.d("----------------BBBBBBBBBBB-------------------");
        activityBTv.setText("我是从AvtivityA跳转过来的");
    }
}
