package com.example.apple.yunqiao_weex.Activity.MVP;

import com.example.apple.yunqiao_weex.Activity.MVP.Base.BaseView;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/16 9:56 AM
 * 描述
 */

public class httpPresenter implements TestContract.Presenter {
    httpModel model = new httpModel();
    TestContract.View View;
    public httpPresenter(TestContract.View view) {
        View = view;
    }

    @Override
    public void request1() {

    }

    @Override
    public void request2() {

    }

    @Override
    public void request3() {
        model.getData3(new Callback() {
            @Override
            public void onResult(String text) {
                if ("-1".equals(text)) {
                    View.showError("网络错误");
                } else {
                    View.updateUI3(text);
                }
            }
        });
    }
}
