package com.example.apple.yunqiao_weex.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apple.yunqiao_weex.R;
import com.example.apple.yunqiao_weex.Weex.WeexEmptyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 对于MVP的理解及使用
 */

public class MainActivity extends BasicYunQiaoActivity {

    @BindView(R.id.btn_weex)
    Button btnWeex;
    @BindView(R.id.btn_Designer)
    Button btnDesigner;
    @BindView(R.id.btn_custom_view)
    Button btnCustomView;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void prepareData() {

    }

    @OnClick({R.id.btn_weex, R.id.btn_Designer, R.id.btn_custom_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_weex:
                Intent intent = new Intent(this, WeexEmptyActivity.class);
                intent.putExtra("URL","index.js");
                startActivity(intent);
                break;
            case R.id.btn_Designer:
                break;
            case R.id.btn_custom_view:
                break;
        }
    }
}

