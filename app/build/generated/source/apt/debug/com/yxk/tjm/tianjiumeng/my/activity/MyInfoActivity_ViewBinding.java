// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInfoActivity_ViewBinding implements Unbinder {
  private MyInfoActivity target;

  private View view2131624137;

  private View view2131624136;

  @UiThread
  public MyInfoActivity_ViewBinding(MyInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyInfoActivity_ViewBinding(final MyInfoActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.relative_address, "field 'relativeAddress' and method 'onClick'");
    target.relativeAddress = Utils.castView(view, R.id.relative_address, "field 'relativeAddress'", RelativeLayout.class);
    view2131624137 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.relative_head, "field 'relativeHead' and method 'onClick'");
    target.relativeHead = Utils.castView(view, R.id.relative_head, "field 'relativeHead'", RelativeLayout.class);
    view2131624136 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.relativeAddress = null;
    target.relativeHead = null;
    target.toolbar = null;

    view2131624137.setOnClickListener(null);
    view2131624137 = null;
    view2131624136.setOnClickListener(null);
    view2131624136 = null;
  }
}
