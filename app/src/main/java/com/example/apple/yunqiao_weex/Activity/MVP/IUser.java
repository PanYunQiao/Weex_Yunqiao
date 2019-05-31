package com.example.apple.yunqiao_weex.Activity.MVP;

/**
 * 姓名    PanJiangHao
 * 时间    2019/5/29 10:22 AM
 * 描述
 */

public interface IUser {
     String getUsername();
     String getPWD();
     boolean checkLoginVisible(String username,String pwd);
}
