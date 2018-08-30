package com.limin.myapplication3.refresh.flyrefresh;

import android.content.Context;
import android.util.AttributeSet;

import com.limin.myapplication3.refresh.internal.pathview.PathsView;
import com.limin.myapplication3.refresh.util.DensityUtil;

/**
 * 纸飞机视图
 * Created by SCWANG on 2017/6/6.
 */
public class FlyView extends PathsView {

    public FlyView(Context context) {
        this(context, null);
    }
    
    public FlyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.parserColors(0xffffffff);
        if (!mPathsDrawable.parserPaths("M2.01,21L23,12 2.01,3 2,10l15,2 -15,2z")) {
            mPathsDrawable.declareOriginal(2, 3, 20, 18);
        }
        int side = DensityUtil.dp2px(25);
        mPathsDrawable.setBounds(0, 0, side, side);
    }
}
