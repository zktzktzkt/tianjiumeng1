package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ningfei on 2017/3/7.
 */

public class NoScrollViewPager extends ViewPager {

    private Context context;
    private boolean willIntercept = false;

    public NoScrollViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(willIntercept){
            //这个地方直接返回true会很卡
            return super.onInterceptTouchEvent(arg0);
        }else{
            return false;
        }

    }

    /**
     * 设置ViewPager是否拦截点击事件
     * @param value if true, ViewPager拦截点击事件
     *                                 if false, ViewPager将不能滑动，ViewPager的子View可以获得点击事件
     *                                 主要受影响的点击事件为横向滑动
     *
     */
    public void setTouchIntercept(boolean value){
        willIntercept = value;
    }


}
