package com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP;

import com.example.apple.yunqiao_weex.Activity.MVP.FinalMVP.Base.BaseView;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/20 5:20 PM
 * 描述    契约接口
 */

public interface Contract {

    interface Modle {
        void login(Callback callback);
    }

    interface View extends BaseView {
        void loginSuccess(String msg);

        @Override
        void shoError();
    }

    interface Presenter {
        void login();
    }
}
