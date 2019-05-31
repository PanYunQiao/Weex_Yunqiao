package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/18 1:07 PM
 * 描述
 */

public class ScrollerIViewPage extends ViewGroup {
    private static final String TAG = "ScrollerIViewPage";
    /**
     * 上一次的X坐标点
     */
    private int mLastX;

    public ScrollerIViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int child = getChildCount();
        Log.e(TAG, "--l-->" + l + ",--t-->" + t + ",-->r-->" + r + ",--b-->" + b);
        for (int i = 0; i < child; i++) {
            View view = getChildAt(i);
            view.layout(i * getWidth(), t, (i + 1) * getWidth(), b);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;//点击就保存当前坐标点
                break;
            case MotionEvent.ACTION_MOVE:
                    int dx = mLastX - x;//用之前点坐标x,减去现在点坐标x得到滑动（偏移 ）了多少距离
                    int oldScrollX = getScrollX();//保存原来的偏移量，第一次为0
                    int preScrollX = oldScrollX + dx;//本次滑动后形成的偏移量，假设上次偏移100那么加上本次dx你滑动了50，那么preScrollX = 150；
                    if (preScrollX > (getChildCount() - 1) * getWidth()) {
                        preScrollX = (getChildCount() - 1) * getWidth();
                    }
                    if (preScrollX < 0) {
                        preScrollX = 0;
                    }
                    scrollTo(preScrollX,getScrollY());
                Log.e(TAG, "onTouchEvent: "+x+"------"+mLastX+"-----"+dx+"-----"+oldScrollX+"------"+preScrollX);
                mLastX = x;
                break;
        }
        return true;
    }
}
