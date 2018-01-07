package com.yxk.tjm.tianjiumeng.custom;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by ningfei on 2017/3/9.
 */

public class MyNestedScrollView extends NestedScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;

    public MyNestedScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }

    /**
     * @param headLayout
     * 头部布局
     * @param imageview
     * 标题
     */
    Toolbar toolbar;
    View headView;

    public void setTitleAndHead(Toolbar toolbar, View headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (headView != null && toolbar != null) {
            float headHeight = headView.getMeasuredHeight()
                    - toolbar.getMeasuredHeight();
            int alpha = (int) (((float) t / headHeight) * 255);
            if (alpha >= 255)
                alpha = 255;
            if (alpha <= 0)
                alpha = 0;
            toolbar.getBackground().mutate().setAlpha(alpha);
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
