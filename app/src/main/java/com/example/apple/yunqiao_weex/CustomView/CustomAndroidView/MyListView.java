package com.example.apple.yunqiao_weex.CustomView.CustomAndroidView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.apple.yunqiao_weex.Adapter.MyAdapter;
import com.example.apple.yunqiao_weex.CustomView.Listener.OnDeleteListener;
import com.example.apple.yunqiao_weex.R;

import java.util.List;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/17 2:58 PM
 * 描述    练习继承控件的写法，熟悉GestureDetector手势监听类的使用
 */

public class MyListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private static final String TAG = "MyListView";
    /**
     * 手势监听类
     */
    private GestureDetector gestureDetector;

    /**
     * 删除回调
     */
    private OnDeleteListener listener;

    /**
     * 删除按钮
     */
    View deleteButton;

    ViewGroup itemLayout;

    /**
     * 选中的item
     */
    private int selectedItem;

    /**
     * 删除是否显示
     */
    private boolean isDeleteShown;

    private boolean isClickItem;

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //如果删除按钮显示就删除,不显示就交给GestureDetector这个手势类来操作
        if (isDeleteShown) {
            itemLayout.removeView(deleteButton);
            deleteButton = null;
            isDeleteShown = false;
            return false;
        } else {
            return gestureDetector.onTouchEvent(event);
        }
    }

    /**
     * 对外提供的删除回调方法
     *
     * @param l
     */
    public void setOnDeleteListener(OnDeleteListener l) {
        listener = l;
    }

    //下面这一系列都属于GestureDetector，类似于onTouchEvent，按下，离开等等，本次重点使用onDown跟onFling

    /**
     * 用户按下屏幕的时候的回调。
     * 按下。返回值表示事件是否处理
     *
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        //如果没显示，依据x,y坐标通过pointToPosition来确定当前选中的是那一行item，
        isClickItem = true;
        if (!isDeleteShown) {
            selectedItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        Log.i(TAG, "onDown: " + "用户按下屏幕");
        return false;
    }

    /**
     * 用户按下按键后100ms（根据Android7.0源码）还没有松开或者移动就会回调，官方在源码的解释是说一般用于告诉用户已经识别按下事件的回调
     * 短按(手指尚未松开也没有达到scroll条件)
     *
     * @param e
     */
    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "onShowPress: " + "用户按下按键后100ms");
    }

    /**
     * 轻触(手指松开)
     * 从名子也可以看出,一次单独的轻击抬起操作,也就是轻击一下屏幕，立刻抬起来，才会有这个触发，
     * 当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以也就不会触发这个事件
     * 用户手指松开（UP事件）的时候如果没有执行onScroll()和onLongPress()这两个回调的话，
     * 就会回调这个，说明这是一个点击抬起事件，但是不能区分是否双击事件的抬起。
     *
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "onSingleTapUp: " + "用户轻触松开");
        return false;
    }

    /**
     * 滑动(一次完整的事件可能会多次触发该函数)。返回值表示事件是否处理
     * 在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法
     * 在ACTION_MOVE动作发生时就会触发
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i(TAG, "onScroll: " + "用户一次完整的滑动");
        return false;
    }

    /**
     * 用户长按后（好像不同手机的时间不同，源码里默认是100ms+500ms）触发，触发之后不会触发其他回调，直至松开（UP事件）。
     *
     * @param e
     */
    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "onLongPress: " + "用户长按触发");
    }

    /**
     * 滑屏(用户按下触摸屏、快速滑动后松开，返回值表示事件是否处理)
     * 滑屏：手指触动屏幕后，稍微滑动后立即松开
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //如果删除按钮没有显示，且滑动的velocityX>velocityY,那么条件成立加载布局按钮进行操作
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteButton = LayoutInflater.from(getContext()).inflate(R.layout.delete_button, null);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShown = false;
                    listener.onDelete(selectedItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(selectedItem
                    - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton, params);
            isDeleteShown = true;
        }
        Log.i(TAG, "onFling: " + "用户滑动后松开触发");
        return false;
    }

    /**
     * 返回最后一个点击的Item的Position
     * @return isClickItem为false，则默认返回0；否则返回点击的那个position
     */
    @NonNull
    public int getItemPosition() {
        if (isClickItem) {
            return selectedItem;
        } else {
            return 0;
        }
    }
}
