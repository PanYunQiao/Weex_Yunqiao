package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/15 11:19 AM
 * 描述
 */

public class MaskFilterView extends View {
    private Paint paint;
    private Rect mRect; //用于绘制View大小
    private TextPaint mTextPaint;
    private BlurMaskFilter bmf1;
    private BlurMaskFilter bmf2;
    private BlurMaskFilter bmf3;
    private BlurMaskFilter bmf4;

    public MaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(64);
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mRect = new Rect();

        bmf1 = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
        bmf2 = new BlurMaskFilter(100, BlurMaskFilter.Blur.SOLID);
        bmf3 = new BlurMaskFilter(100, BlurMaskFilter.Blur.OUTER);
        bmf4 = new BlurMaskFilter(100, BlurMaskFilter.Blur.INNER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0, 200);
        canvas.drawColor(Color.WHITE);
//        paint.setMaskFilter(bmf1);
//        canvas.drawCircle(300, 300, 150, paint);
//        canvas.drawText("NORMAL", 300, 600, mTextPaint);

        paint.setMaskFilter(bmf2);
        canvas.drawRect(0,0,1000,1000,paint);
//        canvas.drawCircle(300, 900, 150, paint);
//        canvas.drawText("SOLID", 300, 1200, mTextPaint);

//        paint.setMaskFilter(bmf3);
//        canvas.drawCircle(800, 300, 150, paint);
//        canvas.drawText("OUTER", 800, 600, mTextPaint);

//        paint.setMaskFilter(bmf4);
//        canvas.drawCircle(800, 900, 150, paint);
//        canvas.drawText("INNER", 800, 1200, mTextPaint);
    }
}

