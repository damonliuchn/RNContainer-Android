# RNContainer-Android

TODO:
action index.js  改为main.js
counter.js 改为main.js
1、download 管理，即容器可以下载远程bundle

component->action->dispatch->store->reducer->state->component
组件之间传递值方法 https://www.jianshu.com/p/f908fdc654b4
this作用域问题
https://blog.csdn.net/u013179982/article/details/78497384
react Component自动将render()等固定函数的this指向Component,Vue也是同理
react 对于自定义的函数则this还是执行函数对象本身，需要bind到Component上或者使用箭头函数自动bind
bind(this)的含义是 将函数的this对象指向当前环境里的this
<Provider store> 使组件层级中的 connect() 方法都能够获得 Redux store。正常情况下，你的根组件应该嵌套在 <Provider> 中才能使用 connect() 方法。
传递store //http://react-china.org/t/react-redux-redux-store/11242


debug下 点击音量键 或者  摇晃手机 弹出debug dialog可以更改debug server host



RnUtil.setDebugHost(this, "192.168.10.1:8081");
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

RnPageActivity.startFrom(this, "index.js", null);




## RUN
```bash
npm run serveAndDebug
npm run build
```
## 升级：
https://stackoverflow.com/questions/48756550/unable-to-resolve-module-accessibilityinfo-when-trying-to-create-release-bund
处理升级时的包不匹配的方法：重新init一次  然后查看各版本号
npm uninstall -g react-native-cli
npm install -g react-native-cli
react-native init

## 打包注意
1、入口文件必须放在根目录下，因为react-native/local-cli/cli.js start 默认找根目录
2、react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.js
入口文件名和输出文件名尽量保持一致，输出文件后缀随便


> 此工程是基于react-native Android sdk封装的运行bundle的容器，本人建议使用Weex来代替react-native，下面是weex和react-native开发时的差异点。

## 一、weex 和 react-native 开发时的差异点

![](https://raw.githubusercontent.com/MasonLiuChn/RNContainer-Android/master/blog.png)

RN npm 命令功能：
一、start
1、服务器（热更新）  app<---->服务器  socket通信
2、debug             app<---->服务器<----> chrome

Weex npm 命令功能：
一、serve
1、服务器

二、debug
1、debug             app<---->服务器<----> chrome

即RN的start= weex的serve+debug

## 二、WeexContainer-Android，一个Android平台上Weex容器，实现MPA,Bundle缓存、验签等通用功能。
https://github.com/MasonLiuChn/WeexContainer-Android

## 实时加载（Live Reload）和热加载（Hot Reload）的区别
实时加载应用更新时需要刷新当前页面，可以看到明显的全局刷新效果，状态重置，而热加载基本上看不出刷新的效果，类似于局部刷新，状态不变
热加载的基础是模块热替换（HMR，Hot Module Replacement[3]），HMR 最开始是由 Webpack 引入的，我们在 React Native Packager 中也实现了这个功能。HMR 使得 Packager 可以监控文件的改动并发送 HMR 更新消息（HMR update）给包含在 APP 中的一层很薄的 HMR 运行时（HMR runtime）。

https://www.jianshu.com/p/1fa6e9c0799f#fn2