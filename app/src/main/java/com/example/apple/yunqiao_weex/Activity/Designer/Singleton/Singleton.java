package com.example.apple.yunqiao_weex.Activity.Designer.Singleton;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 3:50 PM
 * 描述    采用类级内部类
 * 推荐使用
 */

public class Singleton {

    private Singleton(){}

    /**
     *    类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     *    没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
     */

    public static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        public static Singleton singleton = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.singleton;
    }
}
