package com.example.apple.yunqiao_weex.Application;

import com.example.apple.yunqiao_weex.Adapter.WxImageAdapter;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/4 1:29 PM
 * 描述 :
 * 注意要在Manifest中启用
 * 参考manifest，否则会抛出ExceptionInInitializerError
 * 要实现ImageAdapter 否则图片不能下载
 * gradle 中一定要添加一些依赖，否则初始化会失败。
 * compile 'com.android.support:recyclerview-v7:23.1.1'
 * compile 'com.android.support:support-v4:23.1.1'
 * compile 'com.android.support:appcompat-v7:23.1.1'
 * compile 'com.alibaba:fastjson:1.1.45'
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initWX();
    }

    public void initWX() {
        //初始化图片加载框架
        InitConfig config = new InitConfig.Builder().setImgAdapter(new WxImageAdapter()).build();
        WXSDKEngine.initialize(this, config);

        //自定义的Module，及Component都需要在这里初始化
//        try {
//            WXSDKEngine.registerModule("openQQ", OpenQQ.class);
//            WXSDKEngine.registerComponent("testComponent", TestComponent.class);
//        } catch (WXException e) {
//            e.printStackTrace();
//        }
    }
}
