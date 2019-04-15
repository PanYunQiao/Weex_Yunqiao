package com.example.apple.yunqiao_weex.Activity.Designer.Observer;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 2:18 PM
 * 描述    观察者
 */

public class ConcreteSubject extends Subject {
    public String state = "更新";
    public changeContent changeContent;
    public void change (String newState,changeContent ChangeContent) {
        state = newState;
        //状态更新通知观察者
        this.nodifyObservers(state);
        //忽略此回调
        changeContent = ChangeContent;
        changeContent.datachange(state);
    }
}
