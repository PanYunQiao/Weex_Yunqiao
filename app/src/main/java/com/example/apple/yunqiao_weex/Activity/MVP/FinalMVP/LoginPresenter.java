package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP;

import android.util.Log;

import com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.Base.BasePresenter;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/20 5:23 PM
 * 描述
 */

public class LoginPresenter extends BasePresenter<Contract.View> implements Contract.Presenter {
    LoginModle modle;

    public LoginPresenter() {
        modle = new LoginModle();
    }

    @Override
    public void login() {
        modle.login(new Callback() {
            @Override
            public void ok(String msg) {
                mView.loginSuccess(msg);
            }
        });
    }
}
