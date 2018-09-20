package com.limin.myapplication3.base;

/**
 * Description：View层通用处理接口
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public interface BaseView<T> {
    /**
     * 设置View视图层控件
     * @param presenter 泛型类
     */
    void setPresenter(T presenter);

    /**
     * 请求服务器失败返回数据
     * @param msg 异常信息
     */
    void showErrorMessage(String msg);
}
