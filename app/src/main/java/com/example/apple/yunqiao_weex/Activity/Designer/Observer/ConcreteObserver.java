package com.example.apple.yunqiao_weex.Activity.Designer.Observer;

import android.app.Activity;
import android.util.Log;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 2:22 PM
 * 描述
 */

public class ConcreteObserver implements Observer {
    public String change = "-1";
    /**
     * 更新观察者，使其与被观察者状态一致
     * @param state 更新的内容与被观察者一致
     */
    @Override
    public void Updata(String state) {
        change = state;
        Log.e("观察者", "Updata: "+change);
    }
}
