package com.masonliu.lib_rn.module;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


/**
 * Rn操作Native
 */
public class RnHandleModule extends ReactContextBaseJavaModule {

    private static final String TAG = "RnHandleModule";

    public RnHandleModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void setTitle(String title) {
    }
}