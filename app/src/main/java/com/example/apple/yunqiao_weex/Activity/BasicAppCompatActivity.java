package com.example.apple.yunqiao_weex.Activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.apple.yunqiao_weex.Application.AppManager;
import com.example.apple.yunqiao_weex.R;

import org.greenrobot.eventbus.EventBus;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/12 1:27 PM
 * 描述   所有Activity基类
 */

public abstract class BasicAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //当前Acidity添加到添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        //FEATURE_NO_TITLE 设置当前window视图 没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        beforeLayout();
        getIntentParams();
        initLayout();
        prepareData();
    }


    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
    /**
     * 获取intent附加数据
     */
    protected abstract void getIntentParams();

    /**
     * 初始化布局
     */
    protected abstract void initLayout();

    /**
     * 初始化业务数据
     */
    protected abstract void prepareData();

    /**
     * 页面初始化前
     */
    protected abstract void beforeLayout();
}
