package com.example.remote_maven;

import android.util.Log;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:12/30/22
 */
public class RemoteMavenUtils {
    private static volatile RemoteMavenUtils instance = null;

    private RemoteMavenUtils(){}

    public static RemoteMavenUtils getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (RemoteMavenUtils.class){
            // double checkout
            if(null == instance){
                instance = new RemoteMavenUtils();
            }
        }
     }
     return instance;
    }

    public void getMaven(String maven){
        Log.d("REMOTE_MAVEN：",maven);
    }
}
