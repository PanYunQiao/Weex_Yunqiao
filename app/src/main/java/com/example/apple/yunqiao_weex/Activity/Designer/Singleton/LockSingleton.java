package com.example.apple.yunqiao_weex.Activity.Designer.Singleton;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 3:43 PM
 * 描述    进入方法后，先检查实例是否存在，如果不存在才进行下面的同步块，这是第一重检查，
 *        进入同步块过后，再次检查实例是否存在，如果不存在，就在同步的情况下创建一个实例，这是第二重检查。
 *        这样一来，就只需要同步一次了，从而减少了多次在同步情况下进行判断所浪费的时间。
 *        不建议使用
 */

public class LockSingleton {
    private static LockSingleton lockSingleton = null;

    private LockSingleton() {}

    public static LockSingleton getInstance () {
        ////先检查实例是否存在，如果不存在才进入下面的同步块
        if (lockSingleton == null) {

            //同步块，线程安全的创建实例
            synchronized (LockSingleton.class) {

                //再次检查实例是否存在，如果不存在才真正的创建实例
                if (lockSingleton == null) {
                    lockSingleton = new LockSingleton();
                }
            }
        }
        return lockSingleton;
    }
}
