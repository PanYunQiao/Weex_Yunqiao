package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.Base;

import android.os.Bundle;
import android.util.Log;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicAppCompatActivity;

public abstract class BaseMVPActivity<T extends BasePresenter> extends BasicAppCompatActivity {
    public T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 如果BaseMVPActivity销毁的时候mPresenter有引用也一并销毁
     */
    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        Log.e("BaseMVPActivity", "onDestroy: " );
        super.onDestroy();
    }
}
