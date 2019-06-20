package com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP.Base.BasePresenter;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MVP3Activity extends AppCompatActivity implements TestContract.IView {

    @BindView(R.id.click)
    TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp3);
        ButterKnife.bind(this);

    }

    @Override
    public void UpDataview(String msg) {

    }

    @Override
    public void showError(String error) {

    }

    @OnClick(R.id.click)
    public void onViewClicked() {

    }
}
