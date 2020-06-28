package com.limin.myapplication3.activity.gaodemap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.limin.myapplication3.R;
import com.limin.myapplication3.adapter.BaiduMapAdapter;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;
import com.yinglan.scrolllayout.ScrollLayout;
import com.yinglan.scrolllayout.content.ContentRecyclerView;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/6/15
 */
public class GaoDeMapActivity extends BaseActivity<GaoDeMapPresenter> implements GaoDeMapConstract.View, AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, BaseQuickAdapter.OnItemClickListener, TextWatcher, GeocodeSearch.OnGeocodeSearchListener {


    @BindView(R.id.activity_baidu_map_rel)
    MapView activityBaiduMapRel;
    @BindView(R.id.activity_baidu_map_btn_save)
    Button activityBaiduMapBtnSave;
    @BindView(R.id.activity_baidu_map_edt)
    EditText activityBaiduMapEdt;
    @BindView(R.id.activity_baidu_map_rv)
    ContentRecyclerView activityBaiduMapRv;
    @BindView(R.id.activity_baidu_map_scroll)
    ScrollLayout activityBaiduMapScroll;

    private AMap aMap;
    private BaiduMapAdapter baiduMapAdapter;
    private PoiSearch poiSearch;
    private GeocodeSearch geocodeSearch;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GaoDeMapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_gaode_map;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.start();
        activityBaiduMapRel.onCreate(savedInstanceState);
        // 配置百度Map定位
        aMap = mPresenter.settingBaiduMapOption(activityBaiduMapRel);
        // 配置scroll滑动
        mPresenter.settingScroll(activityBaiduMapScroll);
        // POI周边检索
        poiSearch = new PoiSearch(this,null);
        // 地理编码
        geocodeSearch = new GeocodeSearch(this);
        // 配置适配器
        baiduMapAdapter = new BaiduMapAdapter(R.layout.item_baidu_map);
        // 配置RecyclerView样式
        activityBaiduMapRv.setLayoutManager(new LinearLayoutManager(this));
        // 添加适配器
        activityBaiduMapRv.setAdapter(baiduMapAdapter);
        // 显示地图定位
        mPresenter.settingBaiduMapLocation(aMap);
    }

    @Override
    protected void addListener() throws RuntimeException {
        aMap.setOnCameraChangeListener(this);
        poiSearch.setOnPoiSearchListener(this);
        baiduMapAdapter.setOnItemClickListener(this);
        activityBaiduMapEdt.addTextChangedListener(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {
        activityBaiduMapRel.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    protected GaoDeMapPresenter createPresenter() throws RuntimeException {
        return (GaoDeMapPresenter) new GaoDeMapPresenter(this).Bulider(this);
    }

    @Override
    public void setPresenter(GaoDeMapConstract.Presenter presenter) {
        mPresenter = (GaoDeMapPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        activityBaiduMapRel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        activityBaiduMapRel.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        activityBaiduMapRel.onSaveInstanceState(outState);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        mPresenter.onCameraChangeFinish(cameraPosition.target.longitude,cameraPosition.target.latitude,poiSearch);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        for (int j = 0; j < poiResult.getPois().size(); j++) {
            baiduMapAdapter.setNewData(poiResult.getPois());
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PoiItem poiItem = (PoiItem) adapter.getItem(position);
        assert poiItem != null;
        aMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(
                        new CameraPosition(
                                new LatLng(
                                        poiItem.getLatLonPoint().getLatitude(),
                                        poiItem.getLatLonPoint().getLongitude()),
                                18,
                                0,
                                0)),
                1000,
                null
        );
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(activityBaiduMapEdt.getText().toString().trim())) {
            geocodeSearch.getFromLocationNameAsyn(new GeocodeQuery(activityBaiduMapEdt.getText().toString().trim(), ""));
        }
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        LogUtils.d(geocodeResult.getGeocodeAddressList().size());
        mPresenter.onCameraChangeFinish(geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude(),geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude(),poiSearch);
    }
}
