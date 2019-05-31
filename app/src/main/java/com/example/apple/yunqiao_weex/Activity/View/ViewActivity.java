package com.example.apple.yunqiao_weex.Activity.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends BasicYunQiaoActivity {


    @BindView(R.id.ScrollerLayout)
    Button ScrollerLayout;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void prepareData() {

    }

    @OnClick({R.id.ScrollerLayout,R.id.combination,R.id.inherit,R.id.ScrollerIViewPage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ScrollerLayout:
                startActivity(new Intent(this,ScrollerLayoutActivity.class));
                break;
            case R.id.combination:
                startActivity(new Intent(this,TitleActivity.class));
                break;
            case R.id.inherit:
                startActivity(new Intent(this,MyListViewActivity.class));
                break;
            case R.id.ScrollerIViewPage:
                startActivity(new Intent(this,ScrollerIViewPageActivity.class));
                break;
        }
    }
}
