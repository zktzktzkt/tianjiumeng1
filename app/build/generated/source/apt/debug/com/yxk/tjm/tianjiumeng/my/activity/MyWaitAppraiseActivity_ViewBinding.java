// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWaitAppraiseActivity_ViewBinding implements Unbinder {
  private MyWaitAppraiseActivity target;

  @UiThread
  public MyWaitAppraiseActivity_ViewBinding(MyWaitAppraiseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWaitAppraiseActivity_ViewBinding(MyWaitAppraiseActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyWaitAppraiseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recycler = null;
  }
}
