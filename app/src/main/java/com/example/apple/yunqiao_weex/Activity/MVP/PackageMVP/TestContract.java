package com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP;

import com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP.Base.BaseModel;
import com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP.Base.BasePresenter;
import com.example.apple.yunqiao_weex.Activity.MVP.PackageMVP.Base.BaseView;

/**
 * 姓名    PanJiangHao
 * 时间    2019/6/18 2:43 PM
 * 描述
 */

public interface TestContract {

    interface Model extends BaseModel {
        void getData1(Callback callback1);
    }

    interface IView extends BaseView {
        void UpDataview(String msg);
    }

     abstract class Presenter extends BasePresenter<IView, Model>{
//        abstract void request1();
//        abstract void request2();
        void request3() {
            model.getData1(new Callback() {
                @Override
                public void onResult(String text) {
                    view.UpDataview(text);
                }
            });
        }
    }
}
