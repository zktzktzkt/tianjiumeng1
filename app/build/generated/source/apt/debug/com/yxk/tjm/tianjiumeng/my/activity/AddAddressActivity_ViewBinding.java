// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddAddressActivity_ViewBinding implements Unbinder {
  private AddAddressActivity target;

  private View view2131624084;

  @UiThread
  public AddAddressActivity_ViewBinding(AddAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddAddressActivity_ViewBinding(final AddAddressActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.btn_add_address, "field 'btnAddAddress' and method 'onClick'");
    target.btnAddAddress = Utils.castView(view, R.id.btn_add_address, "field 'btnAddAddress'", Button.class);
    view2131624084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.btnAddAddress = null;

    view2131624084.setOnClickListener(null);
    view2131624084 = null;
  }
}
