package com.example.apple.yunqiao_weex.Activity.View;

import android.os.Bundle;
import android.view.View;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.CustomView.CustomAndroidView.TitleView;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleActivity extends BasicYunQiaoActivity {
    @BindView(R.id.titleView)
    TitleView titleView;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);
        titleView.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("Back");
                finish();
            }
        });
        titleView.setTitleText("This CustomTitle");
        titleView.setLeftButtonText("Back");
    }

    @Override
    protected void prepareData() {

    }

    @OnClick(R.id.titleView)
    public void onViewClicked() {

    }
}
