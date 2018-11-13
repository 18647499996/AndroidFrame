package com.limin.myapplication3.activity.demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.blankj.utilcode.util.ToastUtils
import com.limin.myapplication3.R
import com.limin.myapplication3.activity.test.TestActivity
import com.limin.myapplication3.base.BaseActivity
import com.limin.myapplication3.model.UserModel
import com.limin.myapplication3.utils.TitleBuilder

import butterknife.BindView

@SuppressLint("Registered")
/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
 class DemoActivitys  : BaseActivity(), DemoContract.View {


    @BindView(R.id.activity_demo_btn)
    internal var activityDemoBtn: Button? = null
    private var mPresenter: DemoContract.Presenter? = null

    override fun getLayout(): Int {
        return R.layout.activity_demo
    }

    override fun initBuilerTitle(): TitleBuilder {
        return TitleBuilder(this)
                .setMiddleTitleBgRes("Demo", R.color.with, R.color.colorAccent)
                .setLeftTextRes("返回", 16, R.color.with)
                .setLeftRelativeLayoutListener(this)
    }

    override fun initData(savedInstanceState: Bundle) {
        immersionBar.transparentStatusBar().statusBarDarkFont(false).init()
        mPresenter = DemoPresenter(this).Bulider(this) as DemoContract.Presenter
        mPresenter!!.start()
    }

    override fun addListener() {
        activityDemoBtn!!.setOnClickListener(this)
    }

    override fun onClickDoubleListener(v: View) {
        when (v.id) {
            R.id.activity_demo_btn ->{
                mPresenter!!.demo()
            }
            else -> {
            }
        }
    }

    override fun onDestroys() {

    }


    override fun setPresenter(presenter: DemoContract.Presenter) {
        mPresenter = BaseActivity.checkNotNull(presenter)
    }

    override fun showErrorMessage(msg: String) {
        ToastUtils.showShort(msg)
    }

    override fun showUserModel(userModel: UserModel) {
        // 获取用户数据更新界面
        TestActivity.startActivity(this)
    }

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, DemoActivitys::class.java)
            context.startActivity(intent)
        }
    }
}
