package com.limin.myapplication3.fragment.mine;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.limin.myapplication3.R;
import com.limin.myapplication3.activity.ActivityA;
import com.limin.myapplication3.activity.bluetooth.BluetoothActivity;
import com.limin.myapplication3.activity.dragtop.DragTopLayoutActivity;
import com.limin.myapplication3.activity.explosionsite.ExplosionSiteActivity;
import com.limin.myapplication3.activity.file.FileManagerActivity;
import com.limin.myapplication3.activity.gaodemap.GaoDeMapActivity;
import com.limin.myapplication3.activity.swipemenu.SwipeMenuRecyclerViewActivity;
import com.limin.myapplication3.activity.videoplay.VideoPlayActivity;
import com.limin.myapplication3.activity.zoomscrollview.ZoomScrollViewActivity;
import com.limin.myapplication3.adapter.BarrageViewAdapter;
import com.limin.myapplication3.base.BaseFragment;
import com.limin.myapplication3.model.BarrageModel;
import com.limin.myapplication3.model.UserModel;
import com.limin.myapplication3.service.BackgroundService;
import com.limin.myapplication3.utils.FingerprintManagerUtils;
import com.limin.myapplication3.utils.LocationUtils;
import com.limin.myapplication3.utils.TitleBuilder;
import com.limin.myapplication3.utils.fngerprint.FingerprintScanHelper;
import com.limin.myapplication3.utils.fngerprint.OnAuthResultListener;
import com.orient.tea.barragephoto.adapter.BarrageAdapter;
import com.orient.tea.barragephoto.model.DataSource;
import com.orient.tea.barragephoto.ui.BarrageView;

import java.util.List;

import butterknife.BindView;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/29 ssssss
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View, MultiLineChooseLayout.onItemClickListener {
    @BindView(R.id.fragment_layout_folw)
    MultiLineChooseLayout fragmentLayoutFolw;
    @BindView(R.id.fragment_mine_barrage)
    BarrageView fragmentMineBarrage;

    FingerprintManagerUtils instance;
    private BarrageAdapter<BarrageModel> barrageAdapter;
    private int page = 1;

    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected TitleBuilder initBuilerTitle(View view) {
        return new TitleBuilder(getActivity(), view).setMiddleTitleBgRes("我的", R.color.black, R.color.with);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.start();
        BarrageView.Options options = new BarrageView.Options()
                // 设置弹幕的位置
                .setGravity(BarrageView.GRAVITY_TOP)
                // 设置弹幕的发送间隔
                .setInterval(50)
                // 设置速度和波动值
                .setSpeed(200, 29)
                // 设置弹幕生成模式
                .setModel(BarrageView.MODEL_COLLISION_DETECTION)
                // 循环播放 默认为1次 -1 为无限循环
                .setRepeat(1)
                // 设置弹幕是否可以点击
                .setClick(true);
        fragmentMineBarrage.setOptions(options);
        barrageAdapter = new BarrageAdapter<BarrageModel>(null,getActivity()) {
            @Override
            protected BarrageViewHolder<BarrageModel> onCreateViewHolder(View root, int type) {
                return new BarrageViewAdapter(root,getActivity());
            }

            @Override
            public int getItemLayout(BarrageModel barrageModel) {
                return R.layout.item_barrage;
            }
        };
        fragmentMineBarrage.setAdapter(barrageAdapter);

    }

    @Override
    protected void onClickDoubleListener(View paramView) {

    }

    @Override
    protected void setListener() {
        fragmentLayoutFolw.setOnItemClickListener(this);
    }

    @Override
    protected MinePresenter createPresenter() throws RuntimeException {
        return (MinePresenter) new MinePresenter(this).Bulider(getActivity());
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = (MinePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showAddView(String[] stringArray) {
        fragmentLayoutFolw.setList(stringArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onItemClick(int position, String text) {
        ToastUtils.showShort(text);
        switch (position) {
            case 0:
                BackgroundService.startService(getContext());
                break;
            case 1:
                SwipeMenuRecyclerViewActivity.startActivity(getActivity());
                break;
            case 2:
                VideoPlayActivity.startActivity(getActivity());
                break;
            case 3:
                BackgroundService.stopService(getContext());
                break;
            case 4:
                ZoomScrollViewActivity.startActivity(getActivity());
                break;
            case 5:
                DragTopLayoutActivity.startActivity(getActivity());
                break;
            case 6:
                ActivityA.startActivity(getActivity());
                break;
            case 7:
//                new FingerprintScanHelper(getActivity()).startAuth(new OnAuthResultListener() {
//                    @Override
//                    public void onSuccess() {
//                        ToastUtils.showShort("验证成功");
//                    }
//
//                    @Override
//                    public void onInputPwd(String pwd) {
//                        LogUtils.d("验证密码：" + pwd);
//                    }
//
//                    @Override
//                    public void onFailed(String msg) {
//                        LogUtils.d("验证失败：" + msg);
//                    }
//
//                    @Override
//                    public void onDeviceNotSupport() {
//                        LogUtils.d("设备不支持");
//
//                    }
//                },true,true);
//
                instance = FingerprintManagerUtils.getInstance(getActivity());
                instance.init(getActivity());
                instance.encrypt();
                break;
            case 8:
                instance.decrypt();
                break;
            case 9:
                LocationUtils.getInstance().getLocationUtils(getActivity(), false).registerLocationListener(aMapLocation -> LogUtils.d("定位信息：" +
                        aMapLocation.getProvince() + "\n" +
                        aMapLocation.getCity() + "\n" +
                        aMapLocation.getCityCode() + "\n" +
                        aMapLocation.getAddress() + "\n" +
                        aMapLocation.getAdCode() + "\n" +
                        aMapLocation.getLatitude() + "\n" +
                        aMapLocation.getLongitude() + "\n" +
                        aMapLocation.getDistrict() + "\n" +
                        aMapLocation.getStreet() + aMapLocation.getStreetNum() + "\n" +
                        aMapLocation.getTime() + "\n" +
                        aMapLocation.getLocationType() + "\n" +
                        aMapLocation.getPoiName() + "\n" +
                        aMapLocation.getAoiName() + "\n" +
                        aMapLocation.getLocationDetail())).start();
                break;
            case 10:
                LocationUtils.getInstance().stop();
                break;
            case 11:
                GaoDeMapActivity.startActivity(getActivity());
                break;
            case 12:
                FileManagerActivity.startActivity(getActivity());
                break;
            case 13:
                page ++;
                BarrageModel barrageModel = new BarrageModel();
                barrageModel.setText("这是一条弹幕：" + page);
                barrageAdapter.add(barrageModel);
                break;
            case 14:
                BluetoothActivity.startActivity(getActivity());
                break;
            case 15:
                ExplosionSiteActivity.startActivity(getActivity(),ExplosionSiteActivity.class);
                break;
            default:
                break;
        }
    }
}
