// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.category.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.MyRadioGroup;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RightHeaderListActivity_ViewBinding implements Unbinder {
  private RightHeaderListActivity target;

  @UiThread
  public RightHeaderListActivity_ViewBinding(RightHeaderListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RightHeaderListActivity_ViewBinding(RightHeaderListActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.rbSaleAmount = Utils.findRequiredViewAsType(source, R.id.rb_sale_amount, "field 'rbSaleAmount'", RadioButton.class);
    target.ivSaleAmount = Utils.findRequiredViewAsType(source, R.id.iv_sale_amount, "field 'ivSaleAmount'", ImageView.class);
    target.rbPrice = Utils.findRequiredViewAsType(source, R.id.rb_price, "field 'rbPrice'", RadioButton.class);
    target.ivPrice = Utils.findRequiredViewAsType(source, R.id.iv_price, "field 'ivPrice'", ImageView.class);
    target.rbNewProduct = Utils.findRequiredViewAsType(source, R.id.rb_new_product, "field 'rbNewProduct'", RadioButton.class);
    target.myRadioGroup = Utils.findRequiredViewAsType(source, R.id.my_radio_group, "field 'myRadioGroup'", MyRadioGroup.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RightHeaderListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.rbSaleAmount = null;
    target.ivSaleAmount = null;
    target.rbPrice = null;
    target.ivPrice = null;
    target.rbNewProduct = null;
    target.myRadioGroup = null;
    target.recycler = null;
  }
}
