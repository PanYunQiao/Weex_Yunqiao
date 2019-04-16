package com.example.apple.yunqiao_weex.Activity.MVP;

import com.example.apple.yunqiao_weex.Activity.MVP.Base.BaseModel;
import com.example.apple.yunqiao_weex.Activity.MVP.Base.BasePresenter;
import com.example.apple.yunqiao_weex.Activity.MVP.Base.BaseView;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 7:59 PM
 * 描述   契约接口
 */

public interface TestContract {
    interface Model extends BaseModel {
        void getData1(Callback callback1);

        void getData2(Callback callback2);

        void getData3(Callback callback3);
    }

    interface View extends BaseView {
        void updateUI1();

        void updateUI2();

        void updateUI3(String txt);
    }

    interface Presenter extends BasePresenter {
        void request1();

        void request2();

        void request3();
    }
}

