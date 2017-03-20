// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WaitPayMoneyCustomFragment_ViewBinding implements Unbinder {
  private WaitPayMoneyCustomFragment target;

  @UiThread
  public WaitPayMoneyCustomFragment_ViewBinding(WaitPayMoneyCustomFragment target, View source) {
    this.target = target;

    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WaitPayMoneyCustomFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recycler = null;
  }
}
