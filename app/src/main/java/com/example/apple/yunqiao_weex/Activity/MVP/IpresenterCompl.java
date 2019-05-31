package com.example.apple.yunqiao_weex.Activity.MVP;

import android.content.Context;
import android.widget.Toast;

/**
 * 姓名    PanJiangHao
 * 时间    2019/5/29 10:19 AM
 * 描述
 */

public class IpresenterCompl extends IPresenter{
    private IUser iUser;
    private ILoginView loginViews;
    @Override
    public void login(String user, String pwd) {
        boolean ret=iUser.checkLoginVisible(user,pwd);
        loginViews.LoginResult(ret,"0");
    }

    public IpresenterCompl(ILoginView loginView) {
        loginViews = loginView;
        initUser();
    }

    private void initUser(){
        iUser=new UserModle("username","pwd");
    }
}
