package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.Base.BaseMVPActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinaMVPActivity extends BaseMVPActivity<LoginPresenter> implements Contract.View {

    @BindView(R.id.click)
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fina_mvp);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void prepareData() {

    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void loginSuccess(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void shoError() {

    }

    @OnClick(R.id.click)
    public void onViewClicked() {
        mPresenter.login();
    }
}
