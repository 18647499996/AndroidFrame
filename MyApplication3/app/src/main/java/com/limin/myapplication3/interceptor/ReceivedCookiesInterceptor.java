package com.limin.myapplication3.interceptor;

/**
 * Created by anfs on 22/03/2017.
 */

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.limin.myapplication3.utils.Constant;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * This Interceptor add all received Cookies to the app DefaultPreferences.
 * Your implementation on how to save the Cookies on the Preferences MAY VARY.
 * <p>
 * Created by tsuharesu on 4/1/15.
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (originalResponse != null && !originalResponse.headers("Set-Cookie").isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (String header : originalResponse.headers("Set-Cookie")) {
                builder.append(header);
                builder.append("|");
            }
            String cookie = builder.toString();
            if (!TextUtils.isEmpty(cookie)) {
                SPUtils.getInstance().put(Constant.COOKIE_VALUE,cookie);
            }
        }
        return originalResponse;
    }
}

//
//
//
//
//
//
//package cn.youth.news.network.impl;
//
//    /**
//     * Created by anfs on 22/03/2017.
//     */
//
//    import android.text.TextUtils;
//    import cn.youth.news.model.CookieBean;
//    import cn.youth.news.news.UIUtils.SP2Util;
//    import cn.youth.news.news.UIUtils.SPK;
//    import cn.youth.news.utils.JsonUtils;
//    import cn.youth.news.utils.RunUtils;
//    import cn.youth.news.service.log.Logcat;
//    import java.io.IOException;
//    import okhttp3.Interceptor;
//    import okhttp3.Response;
//
///**
// * This Interceptor add all received Cookies to the app DefaultPreferences.
// * Your implementation on how to save the Cookies on the Preferences MAY VARY.
// * <p>
// * Created by tsuharesu on 4/1/15.
// */
//public class ReceivedCookiesInterceptor implements Interceptor {
//  @Override
//  public Response intercept(Chain chain) throws IOException {
//    Response originalResponse = chain.proceed(chain.request());
//    RunUtils.run(() -> {
//      if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//        CookieBean cookieBean = new CookieBean();
//        for (String cookie : originalResponse.headers("Set-Cookie")) {
//          String[] split1 = cookie.split(";");
//          if (split1.length > 1) {
//            if (split1[0].toLowerCase().equals("token")) {
//              cookieBean.cookie = split1[1];
//            } else if (split1[0].toLowerCase().equals("token_id")) {
//              cookieBean.cookie = split1[1];
//            }
//          }
//        }
//        SP2Util.putString(SPK.COOKIE_BEAN, JsonUtils.toJson(cookieBean));
//        Logcat.t("cookies").json(JsonUtils.toJson(cookieBean));
//      }
//    });
//    return originalResponse;
//  }
//}