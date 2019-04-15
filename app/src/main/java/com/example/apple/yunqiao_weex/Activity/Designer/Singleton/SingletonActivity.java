package com.example.apple.yunqiao_weex.Activity.Designer.Singleton;

import android.view.View;
import android.widget.Button;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingletonActivity extends BasicYunQiaoActivity {


    @BindView(R.id.Hungry)
    Button Hungry;
    @BindView(R.id.Lazy)
    Button Lazy;
    @BindView(R.id.lock)
    Button lock;
    @BindView(R.id.Class)
    Button Class;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_singleton);
        ButterKnife.bind(this);
    }

    @Override
    protected void prepareData() {

    }

    @OnClick({R.id.Hungry, R.id.Lazy, R.id.lock,R.id.Class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Hungry:
                show(EagerSingleton.getInstance().toString());
                break;
            case R.id.Lazy:
                show(LazySingleton.getInstance().toString());
                break;
            case R.id.lock:
                show(LockSingleton.getInstance().toString());
                break;
            case R.id.Class:
                show(Singleton.getInstance().toString());
                break;
        }
    }

}
