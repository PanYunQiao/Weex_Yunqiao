package com.example.apple.yunqiao_weex.Activity.MVP;

import android.os.Handler;
import android.os.Message;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/16 9:57 AM
 * 描述
 */

public class httpModel implements TestContract.Model {

    @Override
    public void getData1(Callback callback1) {

    }

    @Override
    public void getData2(Callback callback2) {

    }

    @Override
    public void getData3(final Callback callback3) {
        callback3.onResult("网络请求成功");
    }

}
