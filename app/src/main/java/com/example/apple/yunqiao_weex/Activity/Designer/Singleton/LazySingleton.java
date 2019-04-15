package com.example.apple.yunqiao_weex.Activity.Designer.Singleton;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 3:40 PM
 * 描述    懒汉式单例
 */

public class LazySingleton {
    private static LazySingleton lazySingleton = null;

    private LazySingleton () {}

    //同时只能有一个访问
    public static synchronized LazySingleton getInstance () {

        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
