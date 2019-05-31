package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/16 7:17 PM
 * 描述    简易版的viewPage
 */

public class ScrollerLayout extends ViewGroup {
    private static final String TAG = "ScrollerLayout";
    /**
     * 用于完成滚动操作的实例
     */
    Scroller scroller;

    /**
     * 判定为拖动的最小移动像素
     */
    private int mTouchSlop;

    /**
     * 手机按下时的坐标
     */
    private float mXDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //第一步，创建Scroller实例
        scroller = new Scroller(context);
        //获取TouchSlop的值，滑动距离的值
        mTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取子控件的数量
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                View childView = getChildAt(i);
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            // 初始化左右边界值
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();
            Log.e(TAG, "onLayout: "+leftBorder+"--------"+rightBorder);
        }
    }

    /**
     * onInterceptTouchEvent()是ViewGroup的一个方法，
     * 目的是在系统向该ViewGroup及其各个childView触发onTouchEvent()之前对相关事件进行一次拦截.
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {
                    Log.e(TAG, "onInterceptTouchEvent: "+"---------------"+"滚动1");
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    Log.e(TAG, "onInterceptTouchEvent: "+"---------------"+"滚动2");
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    Log.e(TAG, "onInterceptTouchEvent: "+"---------------"+"滚动3"+scrolledX);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                Log.e(TAG, "onTouchEvent: "+targetIndex+"-----"+getScaleX()+"------"+getWidth()+"--------dx"+dx);
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                Log.e(TAG, "onInterceptTouchEvent: "+"---------------"+"滚动4");
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            Log.e(TAG, "onInterceptTouchEvent: "+"---------------"+"滚动5");
            invalidate();
        }
    }
}
