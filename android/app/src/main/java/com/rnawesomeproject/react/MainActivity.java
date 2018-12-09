package com.rnawesomeproject.react;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.masonliu.lib_rn.RnPageActivity;
import com.masonliu.lib_rn.utils.RnUtil;
import com.rnawesomeproject.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RnUtil.setDebugHost(this, "192.168.10.1:8081");
        RnUtil.setBridgePackage(new ReactPackage() {
            @Override
            public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
                return new ArrayList<>();
            }

            @Override
            public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
                return new ArrayList<>();
            }
        });
    }


    public void openIndexPage(View view) {
        RnPageActivity.startFrom(this, "index.js");
    }

}
