# RNContainer-Android

TODO:
1、可以debug
2、容器可以下载远程bundle
3、redux接入


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