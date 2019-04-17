package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.R;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/17 11:26 AM
 * 描述    练习组合控件
 */

public class TitleView extends FrameLayout {

    private Button button;

    private TextView textView;

    public TitleView(@NonNull final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.title,this);
        textView = view.findViewById(R.id.text);
        button = view.findViewById(R.id.button_lfet);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
    }

    public void setTitleText(String txt) {
        textView.setText(txt);
    }

    public void setLeftButtonText(String txt) {
        button.setText(txt);
    }

    public void setLeftButtonListener(OnClickListener l) {
        button.setOnClickListener(l);
    }
}
