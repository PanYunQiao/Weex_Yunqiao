package com.example.apple.yunqiao_weex.Activity.Designer.Singleton;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 3:36 PM
 * 描述    饿汉式单例
 */

public class EagerSingleton {
    private static EagerSingleton eagerSingleton = new EagerSingleton();

    /**
     * 私有默认构造
     */
    private EagerSingleton () {}


    public static EagerSingleton getInstance() {
        return eagerSingleton;
    }
}
