package com.masonliu.lib_rn;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.masonliu.lib_rn.utils.CommonUtil;
import com.masonliu.lib_rn.utils.RnInstanceManager;

/**
 * Created by liumeng02 on 2018/3/9.
 * eg: http://index.android.bundle/MainComponent/coupon?launchOption={"key":"value"}
 */

public class RnPageActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private static final String KEY_URI = "URI";
    private final int OVERLAY_PERMISSION_REQ_CODE = 1;  // 任写一个值
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    private String mUri;

    public static void startFrom(Context activity, String uri, String backupsName) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        Intent intent = new Intent(activity, RnPageActivity.class);
        intent.putExtra(KEY_URI, uri);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        mUri = getIntent().getStringExtra(KEY_URI);
        mReactRootView = new ReactRootView(this);
        mReactInstanceManager = RnInstanceManager.getInstance(getApplication()).getReactInstanceManager(mUri);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                .setApplication(getApplication())
//                .setBundleAssetName("index.android.bundle")
//                .setJSMainModulePath("index.android")
//                .addPackage(new MainReactPackage())
//                .setUseDeveloperSupport(BuildConfig.DEBUG)
//                .setInitialLifecycleState(LifecycleState.RESUMED)
//                .build();

        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "main", null);

        setContentView(mReactRootView);

        if (CommonUtil.isApkDebugable(this) &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }


    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (CommonUtil.isApkDebugable(this) &&
                (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) &&
                mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    Toast.makeText(this, "SYSTEM_ALERT_WINDOW permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
