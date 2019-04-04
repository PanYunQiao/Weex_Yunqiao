package com.example.apple.yunqiao_weex.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.R;
import com.example.apple.yunqiao_weex.Weex.WeexEmptyActivity;

/**
 * 对于MVP的理解及使用
 */
interface MVPView{
    void upDateTv(String text);
}
public class MainActivity extends AppCompatActivity implements MVPView{
    TextView tv;
    Button btn;
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        presenter = new Presenter(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                presenter.result();
                Intent intent = new Intent(MainActivity.this, WeexEmptyActivity.class);
                intent.putExtra("URL","index.js");
                startActivity(intent);
            }
        });
    }

    @Override
    public void upDateTv(String text) {
        tv.setText(text);
    }

    interface Callback{
        void onResult(String text);
    }

    public class HttpModle{
        Callback callback;

        HttpModle(Callback callback){
            this.callback = callback;
        }

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                callback.onResult((String) msg.obj);
            }
        };
        public void result(){
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Message message = new Message();
                        message.obj = "从网络获取的数据";
                        handler.sendMessage(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    public class Presenter{
        MVPView view;
        HttpModle modle;

        public Presenter(MVPView view){
            this.view = view;
            modle = new HttpModle(new Callback() {
                @Override
                public void onResult(String text) {
                    Presenter.this.view.upDateTv(text);
                }
            });
        }

        public void result(){
            modle.result();
        }
        private void  detachView(){
            view = null;
        }
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}

