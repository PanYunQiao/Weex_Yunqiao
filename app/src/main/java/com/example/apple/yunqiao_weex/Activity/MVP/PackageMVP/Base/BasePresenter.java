package com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP.Base;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/18 2:40 PM
 * 描述
 */

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    protected V view;
    protected M model;

    public BasePresenter() {
        model = createModel();
    }

    void attachView(V view) {
        this.view = view;
    }

    void detachView() {
        this.view = null;
    }

    abstract M createModel();
}