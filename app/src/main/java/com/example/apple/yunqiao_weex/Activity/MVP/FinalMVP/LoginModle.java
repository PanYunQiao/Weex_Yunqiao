package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/20 5:24 PM
 * 描述
 */

public class LoginModle implements Contract.Modle {
    @Override
    public void login(Callback callback) {
        callback.ok("获取成功");
    }
}
