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
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/2
 */
public interface BasePresenter {

    /**初始化数据*/
     void start();

    /**
     * 释放presenter资源
     */
    void onDestroy();

}
