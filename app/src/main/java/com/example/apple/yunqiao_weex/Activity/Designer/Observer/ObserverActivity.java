package com.example.apple.yunqiao_weex.Activity.Designer.Observer;

import android.widget.Button;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.Activity.Basic.BasicYunQiaoActivity;
import com.example.apple.yunqiao_weex.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObserverActivity extends BasicYunQiaoActivity{
    @BindView(R.id.Observer)
    Button Observer;

    //创建被观察者
    ConcreteSubject concreteSubject = new ConcreteSubject();
    //创建观察者
    Observer observer = new ConcreteObserver();
    @BindView(R.id.change_content)
    TextView changeContent;

    @Override
    protected void getIntentParams() {

    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_observer);
        ButterKnife.bind(this);


        //将观察者注册到对象身上
        concreteSubject.attach(observer);
    }

    @Override
    protected void prepareData() {

    }

    @OnClick(R.id.Observer)
    public void onViewClicked() {
        //被观察者更新内容
        concreteSubject.change("更新了。。。。。", new changeContent() {
            @Override
            public void datachange(String data) {
                //可以忽略此回调，只是用于页面显示
                changeContent.setText(data);
            }
        });
    }
}
