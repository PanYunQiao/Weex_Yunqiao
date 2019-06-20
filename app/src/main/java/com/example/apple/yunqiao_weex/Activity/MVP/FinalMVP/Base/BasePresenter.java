package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.Base;

import android.util.Log;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/20 5:09 PM
 * 描述
 */

public class BasePresenter<V extends BaseView> {
    protected V mView;
    /**
     * 綁定view，一般在初始化中調用
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除綁定view，一般在onDestroy中调用
     */

    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否綁定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
