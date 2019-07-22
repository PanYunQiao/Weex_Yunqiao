package com.example.apple.yunqiao_weex.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.Activity.Designer.DesignerActivity;
import com.example.apple.yunqiao_weex.Activity.FaceBook.FacebookActivity;
import com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.FinaMVPActivity;
import com.example.apple.yunqiao_weex.Activity.View.ViewActivity;
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
    @BindView(R.id.facebook)
    Button facebook;

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

    @OnClick({R.id.btn_weex, R.id.btn_Designer, R.id.btn_custom_view, R.id.btn_MVP, R.id.facebook})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_weex:
                Intent intent = new Intent(this, WeexEmptyActivity.class);
                intent.putExtra("URL", "index.js");
                startActivity(intent);
                break;
            case R.id.btn_Designer:
                startActivity(new Intent(this, DesignerActivity.class));
                break;
            case R.id.btn_custom_view:
                startActivity(new Intent(this, ViewActivity.class));
                break;
            case R.id.btn_MVP:
                startActivity(new Intent(this, FinaMVPActivity.class));
                break;
            case R.id.facebook:
                startActivity(new Intent(this, FacebookActivity.class));
                break;
                default:
                    break;
        }
    }

}

