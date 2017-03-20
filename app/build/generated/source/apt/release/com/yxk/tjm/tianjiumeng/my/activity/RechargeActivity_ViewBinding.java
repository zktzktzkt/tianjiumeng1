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

public class RechargeActivity_ViewBinding implements Unbinder {
  private RechargeActivity target;

  private View view2131624124;

  private View view2131624126;

  private View view2131624163;

  @UiThread
  public RechargeActivity_ViewBinding(RechargeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RechargeActivity_ViewBinding(final RechargeActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ivWeixin = Utils.findRequiredViewAsType(source, R.id.iv_weixin, "field 'ivWeixin'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_weixin, "field 'rlWeixin' and method 'onClick'");
    target.rlWeixin = Utils.castView(view, R.id.rl_weixin, "field 'rlWeixin'", RelativeLayout.class);
    view2131624124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ivAlipay = Utils.findRequiredViewAsType(source, R.id.iv_alipay, "field 'ivAlipay'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_alipay, "field 'rlAlipay' and method 'onClick'");
    target.rlAlipay = Utils.castView(view, R.id.rl_alipay, "field 'rlAlipay'", RelativeLayout.class);
    view2131624126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_recharge, "field 'btnRecharge' and method 'onClick'");
    target.btnRecharge = Utils.castView(view, R.id.btn_recharge, "field 'btnRecharge'", Button.class);
    view2131624163 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RechargeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.ivWeixin = null;
    target.rlWeixin = null;
    target.ivAlipay = null;
    target.rlAlipay = null;
    target.btnRecharge = null;

    view2131624124.setOnClickListener(null);
    view2131624124 = null;
    view2131624126.setOnClickListener(null);
    view2131624126 = null;
    view2131624163.setOnClickListener(null);
    view2131624163 = null;
  }
}
