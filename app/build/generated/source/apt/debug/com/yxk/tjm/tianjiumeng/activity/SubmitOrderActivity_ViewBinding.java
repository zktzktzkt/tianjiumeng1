// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SubmitOrderActivity_ViewBinding implements Unbinder {
  private SubmitOrderActivity target;

  private View view2131624111;

  private View view2131624145;

  @UiThread
  public SubmitOrderActivity_ViewBinding(SubmitOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SubmitOrderActivity_ViewBinding(final SubmitOrderActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tvPerson = Utils.findRequiredViewAsType(source, R.id.tv_person, "field 'tvPerson'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.imgPic = Utils.findRequiredViewAsType(source, R.id.img_pic, "field 'imgPic'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvNum = Utils.findRequiredViewAsType(source, R.id.tv_num, "field 'tvNum'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    target.tvFreight = Utils.findRequiredViewAsType(source, R.id.tv_freight, "field 'tvFreight'", TextView.class);
    target.tvDiscountCoupon = Utils.findRequiredViewAsType(source, R.id.tv_discount_coupon, "field 'tvDiscountCoupon'", TextView.class);
    target.cbCystal = Utils.findRequiredViewAsType(source, R.id.cb_cystal, "field 'cbCystal'", CheckBox.class);
    target.cbInvoice = Utils.findRequiredViewAsType(source, R.id.cb_invoice, "field 'cbInvoice'", CheckBox.class);
    target.tvAllPrice = Utils.findRequiredViewAsType(source, R.id.tv_all_price, "field 'tvAllPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onClick'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131624111 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_person_info, "field 'rlPersonInfo' and method 'onClick'");
    target.rlPersonInfo = Utils.castView(view, R.id.rl_person_info, "field 'rlPersonInfo'", RelativeLayout.class);
    view2131624145 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.heji = Utils.findRequiredViewAsType(source, R.id.heji, "field 'heji'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SubmitOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.tvPerson = null;
    target.tvPhone = null;
    target.tvAddress = null;
    target.imgPic = null;
    target.tvTitle = null;
    target.tvNum = null;
    target.tvPrice = null;
    target.tvFreight = null;
    target.tvDiscountCoupon = null;
    target.cbCystal = null;
    target.cbInvoice = null;
    target.tvAllPrice = null;
    target.btnSubmit = null;
    target.rlPersonInfo = null;
    target.heji = null;

    view2131624111.setOnClickListener(null);
    view2131624111 = null;
    view2131624145.setOnClickListener(null);
    view2131624145 = null;
  }
}
