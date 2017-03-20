package com.yxk.tjm.tianjiumeng.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.yxk.tjm.tianjiumeng.utils.DensityUtil;

/**
 * Created by ningfei on 2017/3/18.
 */

public class MyDialog extends Dialog {

    private Context context;
    private int height, width;
    private boolean cancelTouchout;
    private View view;

    public MyDialog(@NonNull Builder builder) {
        super(builder.context);

        context = builder.context;
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    public MyDialog(@NonNull Builder builder, @StyleRes int themeResId) {
        super(builder.context, themeResId);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        cancelTouchout = builder.cancelTouchout;
        view = builder.view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);

        setCanceledOnTouchOutside(cancelTouchout);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
    }

    public static final class Builder {
        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;
        private int resStyle = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder view(View view) {
            this.view = view;
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder heightdp(int val) {
            height = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder widthdp(int val) {
            width = DensityUtil.dip2px(context, val);
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchout = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }

        public MyDialog build() {
            return resStyle == -1 ? new MyDialog(this) : new MyDialog(this, resStyle);
        }
    }

}
