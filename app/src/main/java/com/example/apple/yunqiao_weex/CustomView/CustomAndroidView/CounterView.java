package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/4 9:51 AM
 * 描述    自绘控件，计数器
 * 测量：onMeasure()决定View的大小；
 * 布局：onLayout()决定View在ViewGroup(也就是布局)中的位置；
 * 绘制：onDraw()决定绘制这个View。
 *
 * 在CounterView，测量和布局都交了父类，因此只实现了onDraw()方法
 */

public class CounterView extends View implements View.OnClickListener{

    private Paint mPaint; //准备画笔用于绘制View上的文字

    private Rect mRect; //用于绘制View大小

    private int mCounter;

    /**
     * 初始化画笔和矩阵
     * @param context
     * @param attrs
     */
    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//初始化画笔，设置flags:Paint.ANTI_ALIAS_FLAG扛锯齿
        mRect = new Rect();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);//设置画笔颜色
        //在画布上画出一个矩阵，上左下右可以自己设置,画布颜色就是上一步画笔的颜色
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
        //现在改变画笔颜色，绘制画笔的数,设置字体大小,getTextBounds获得文字的宽高
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(30);
        String text = String.valueOf(mCounter);
        mPaint.getTextBounds(text,0,text.length(),mRect);
        float textWidth = mRect.width();
        float textHeight = mRect.height();
        canvas.drawText(text,getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mCounter++;
        invalidate();//调用invalidate()方法会导致视图进行重绘，因此onDraw()方法在稍后就将会得到调用。视图会相应更新
    }
}
