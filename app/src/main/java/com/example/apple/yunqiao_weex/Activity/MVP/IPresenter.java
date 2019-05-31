package com.example.apple.yunqiao_weex.Activity.MVP;

import android.content.Context;
import android.widget.Toast;

/**
 * 姓名    PanJiangHao
 * 时间    2019/5/29 10:17 AM
 * 描述
 */

public abstract class IPresenter {
   abstract void login(String user,String pwd);
   void test(Context context){
       Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();
   }
}
