package com.masonliu.lib_rn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.react.ReactPackage;


/**
 * Created by liumeng02 on 2018/11/12.
 */

public class RnUtil {
    //handle module 抛出去
    // debug ip
    // download 管理
    static ReactPackage reactPackage;

    public static void setDebugHost(Context context, String host) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefEditor = sharedPref.edit(); // Get preference in editor mode
        prefEditor.putString("debug_http_host", host); // set your default value here (could be empty as well)
        prefEditor.commit(); // finally save changes
    }

    public static void setBridgePackage(ReactPackage mReactPackage) {
        reactPackage = mReactPackage;
    }
}
