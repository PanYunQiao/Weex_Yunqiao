package com.example.apple.yunqiao_weex.Activity.MVP;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVPActivity extends BasicYunQiaoActivity implements TestContract.View {


    @BindView(R.id.btn_getData)
    Button btnGetData;
    @BindView(R.id.tv_content)
    TextView tvContent;

    httpPresenter httpPresenter;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_mvp);
        ButterKnife.bind(this);
        httpPresenter = new httpPresenter(this);
        tvContent.setText("点击模拟网络请求");
    }

    @Override
    protected void prepareData() {

    }

    @OnClick({R.id.btn_getData, R.id.tv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getData:
                httpPresenter.request3();
                break;
            case R.id.tv_content:
                break;
        }
    }

    @Override
    public void showError(String error) {
        tvContent.setText(error);
    }

    @Override
    public void updateUI1() {

    }

    @Override
    public void updateUI2() {

    }

    @Override
    public void updateUI3(String txt) {
        tvContent.setText(txt);
    }
}
