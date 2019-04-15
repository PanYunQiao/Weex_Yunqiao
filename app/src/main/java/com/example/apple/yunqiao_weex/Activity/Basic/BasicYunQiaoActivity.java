package com.example.apple.yunqiao_weex.Activity.Basic;

import android.widget.Toast;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicAppCompatActivity;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/12 2:05 PM
 * 描述
 */

public abstract class BasicYunQiaoActivity extends BasicAppCompatActivity {

    public void show (String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void beforeLayout() {

    }
}
