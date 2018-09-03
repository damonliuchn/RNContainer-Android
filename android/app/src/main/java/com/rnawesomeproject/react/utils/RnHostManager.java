package com.rnawesomeproject.react.utils;

import android.app.Application;

import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.ViewManager;
import com.rnawesomeproject.react.module.RnHandleModule;
import com.rnawesomeproject.react.view.RnLabelTextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * ------- BundleFile(对应一个ReactNativeHost、一个ReactInstanceManager) ------------- MainComponentName -- launcher option
 * ----------模块 -------------------------------------------------------------------------页面------------参数和ViewGroup
 * 一个BundleFile可以包含多个ComponentName，但建议只包含一个ComponentName，这样可以做BundleFile的拆分
 * 一个Bundle 对应一个 ReactNativeHost, 里边 包含一个ReactInstanceManager
 */
public class RnHostManager {

    private volatile static RnHostManager _instance;
    private Map<String, ReactNativeHost> reactNativeHostMap = new HashMap<>();
    private Application application;

    private RnHostManager(Application application) {
        this.application = application;
    }

    public static RnHostManager getInstance(final Application application) {
        if (_instance == null) {
            synchronized (RnHostManager.class) {
                if (_instance == null) {
                    _instance = new RnHostManager(application);
                }
            }
        }
        return _instance;
    }

    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public ReactNativeHost getReactNativeHost(final String bundleName) {
        if (reactNativeHostMap.get(bundleName) == null) {

            final ReactNativeHost reactNativeHost = new ReactNativeHost(application) {

                @Override
                public boolean getUseDeveloperSupport() {
                    return DebugableUtil.isApkDebugable(application);
                }

                @Override
                protected List<ReactPackage> getPackages() {
                    return Arrays.asList(
                            new MainReactPackage(),
                            new CustomReactPackage()
                    );
                }

                /**
                 * bundle的加载顺序是 dev_server(DeveloperSupport enable,而且加载后还会有缓存) > JSBundleFile > BundleAssetName
                 * @return
                 */
                @Override
                protected String getJSMainModuleName() {//指定debug时的 url的path
                    return getFileNameNoEx(bundleName);
                }

                @Override
                protected String getJSBundleFile() {
                    return null;
                }

                @Nullable
                protected String getBundleAssetName() {
                    return bundleName;
                }
            };
            reactNativeHostMap.put(bundleName, reactNativeHost);
        }
        return reactNativeHostMap.get(bundleName);
    }

    void clearBundleMemoryCache() {
        reactNativeHostMap.clear();
    }

    class CustomReactPackage implements ReactPackage {

        @Override
        public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
            return Arrays.<NativeModule>asList(
                    new RnHandleModule(reactContext)
            );
        }

        @Override
        public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
            return Arrays.<ViewManager>asList(
                    new RnLabelTextView.RnLabelTextViewManager()
            );
        }
    }
}