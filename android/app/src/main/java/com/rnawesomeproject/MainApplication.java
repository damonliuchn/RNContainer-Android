package com.rnawesomeproject;

import android.app.Application;

import com.facebook.soloader.SoLoader;

public class MainApplication extends Application {

//  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
//    @Override
//    public boolean getUseDeveloperSupport() {
//      return BuildConfig.DEBUG;
//    }
//
//    @Override
//    protected List<ReactPackage> getPackages() {
//      return Arrays.<ReactPackage>asList(
//          new MainReactPackage()
//      );
//    }
//
//    @Override
//    protected String getJSMainModuleName() {
//      return "index";
//    }
//  };
//
//  @Override
//  public ReactNativeHost getReactNativeHost() {
//    return mReactNativeHost;
//  }

    @Override
    public void onCreate() {
        super.onCreate();
        //SoLoader.init(this, /* native exopackage */ false);
    }
}
