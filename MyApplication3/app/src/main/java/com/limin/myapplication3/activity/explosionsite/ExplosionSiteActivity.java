package com.limin.myapplication3.activity.explosionsite;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.limin.myapplication3.R;
import com.limin.myapplication3.base.BaseActivity;
import com.limin.myapplication3.utils.TitleBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import sakura.particle.Factory.BooleanFactory;
import sakura.particle.Factory.ExplodeParticleFactory;
import sakura.particle.Factory.FallingParticleFactory;
import sakura.particle.Factory.FlyawayFactory;
import sakura.particle.Factory.InnerFallingParticleFactory;
import sakura.particle.Factory.VerticalAscentFactory;
import sakura.particle.Main.ExplosionSite;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2021/1/22
 */
public class ExplosionSiteActivity extends BaseActivity<ExplosionSitePresenter> implements ExplosionSiteConstract.View {

    @BindView(R.id.activity_explosion_site_img_1)
    ImageView activityExplosionSiteImg1;
    @BindView(R.id.activity_explosion_site_img_2)
    ImageView activityExplosionSiteImg2;
    @BindView(R.id.activity_explosion_site_img_3)
    ImageView activityExplosionSiteImg3;
    @BindView(R.id.activity_explosion_site_img_4)
    ImageView activityExplosionSiteImg4;
    @BindView(R.id.activity_explosion_site_img_5)
    ImageView activityExplosionSiteImg5;
    @BindView(R.id.activity_explosion_site_img_6)
    ImageView activityExplosionSiteImg6;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_explosion_site;
    }

    @Override
    protected TitleBuilder initBuilerTitle() throws RuntimeException {
        return new TitleBuilder(this).setMiddleTitleBgRes("粒子爆炸特效");
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.start();
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @Override
    @OnClick({R.id.activity_explosion_site_img_1, R.id.activity_explosion_site_img_2, R.id.activity_explosion_site_img_3,
            R.id.activity_explosion_site_img_4, R.id.activity_explosion_site_img_5, R.id.activity_explosion_site_img_6})
    protected void onClickDoubleListener(View view) throws RuntimeException {
        switch (view.getId()) {
            case R.id.activity_explosion_site_img_1:
                new ExplosionSite(this,new BooleanFactory()).explode(activityExplosionSiteImg1);
                break;
            case R.id.activity_explosion_site_img_2:
                new ExplosionSite(this, new ExplodeParticleFactory()).explode(activityExplosionSiteImg2);

                break;
            case R.id.activity_explosion_site_img_3:
                new ExplosionSite(this, new FallingParticleFactory()).explode(activityExplosionSiteImg3);

                break;
            case R.id.activity_explosion_site_img_4:
                new ExplosionSite(this, new FlyawayFactory()).explode(activityExplosionSiteImg4);
                break;
            case R.id.activity_explosion_site_img_5:
                new ExplosionSite(this, new InnerFallingParticleFactory()).explode(activityExplosionSiteImg5);
                break;
            case R.id.activity_explosion_site_img_6:
                new ExplosionSite(this, new VerticalAscentFactory()).explode(activityExplosionSiteImg6);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    protected ExplosionSitePresenter createPresenter() throws RuntimeException {
        return (ExplosionSitePresenter) new ExplosionSitePresenter(this).Bulider(this);
    }

    @Override
    public void setPresenter(ExplosionSiteConstract.Presenter presenter) {
        mPresenter = (ExplosionSitePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ToastUtils.showShort(msg);
    }
}
