package com.limin.myapplication3.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.limin.myapplication3.R
import com.limin.myapplication3.base.BaseActivity
import com.limin.myapplication3.utils.TitleBuilder

@SuppressLint("Registered")
/**
 * Description：
 * @author Created by: Li_Min
 * Time:2018/9/18
 */
class Activity:BaseActivity(){
    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initBuilerTitle(): TitleBuilder {
        return TitleBuilder(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickDoubleListener(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getActivity(user:String,nickname:String){

    }

}