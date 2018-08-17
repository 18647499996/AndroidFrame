/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
