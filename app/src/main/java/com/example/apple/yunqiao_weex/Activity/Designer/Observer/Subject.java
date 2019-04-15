package com.example.apple.yunqiao_weex.Activity.Designer.Observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 2:06 PM
 * 描述    观察者模式基类
 */

public abstract class Subject {
    /**
     * 用来保存注册的观察者
     * 增删用LinkedList
     * 查改用ArrayList
     */
    LinkedList<Observer> list = new LinkedList<>();

    /**
     * 注册观察者对象
     * @param observer 观察者对象
     */
    public void attach(Observer observer) {
        list.add(observer);
    }

    /**
     * 删除观察者对象
     * @param observer 要删除的观察者
     */
    public void remove (Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知所有观察者
     * @param state
     */
    public void nodifyObservers(String state) {
        for (Observer observer : list) {
            observer.Updata(state);
        }
    }
}
