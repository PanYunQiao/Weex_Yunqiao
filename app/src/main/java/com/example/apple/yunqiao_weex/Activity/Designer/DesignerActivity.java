package com.example.apple.yunqiao_weex.Activity.Designer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apple.yunqiao_weex.Activity.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.Activity.Designer.Observer.ObserverActivity;
import com.example.apple.yunqiao_weex.Activity.Designer.Singleton.SingletonActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DesignerActivity extends BasicYunQiaoActivity {

    @BindView(R.id.Observer)
    Button Observer;
    @BindView(R.id.Singleton)
    Button Singleton;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_designer);
        ButterKnife.bind(this);
    }

    @Override
    protected void prepareData() {

    }

    @OnClick({R.id.Observer, R.id.Singleton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Observer:
                startActivity(new Intent(this, ObserverActivity.class));
                break;
            case R.id.Singleton:
                startActivity(new Intent(this, SingletonActivity.class));
                break;
        }
    }
}
