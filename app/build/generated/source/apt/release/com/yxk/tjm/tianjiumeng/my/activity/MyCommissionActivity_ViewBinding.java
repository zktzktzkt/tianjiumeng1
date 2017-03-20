// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyCommissionActivity_ViewBinding implements Unbinder {
  private MyCommissionActivity target;

  private View view2131624124;

  private View view2131624126;

  @UiThread
  public MyCommissionActivity_ViewBinding(MyCommissionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCommissionActivity_ViewBinding(final MyCommissionActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.rl_weixin, "field 'rlWeixin' and method 'onClick'");
    target.rlWeixin = Utils.castView(view, R.id.rl_weixin, "field 'rlWeixin'", RelativeLayout.class);
    view2131624124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_alipay, "field 'rlAlipay' and method 'onClick'");
    target.rlAlipay = Utils.castView(view, R.id.rl_alipay, "field 'rlAlipay'", RelativeLayout.class);
    view2131624126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.btnAddAddress = Utils.findRequiredViewAsType(source, R.id.btn_add_address, "field 'btnAddAddress'", Button.class);
    target.ivWeixin = Utils.findRequiredViewAsType(source, R.id.iv_weixin, "field 'ivWeixin'", ImageView.class);
    target.ivAlipay = Utils.findRequiredViewAsType(source, R.id.iv_alipay, "field 'ivAlipay'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyCommissionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.rlWeixin = null;
    target.rlAlipay = null;
    target.btnAddAddress = null;
    target.ivWeixin = null;
    target.ivAlipay = null;

    view2131624124.setOnClickListener(null);
    view2131624124 = null;
    view2131624126.setOnClickListener(null);
    view2131624126 = null;
  }
}
