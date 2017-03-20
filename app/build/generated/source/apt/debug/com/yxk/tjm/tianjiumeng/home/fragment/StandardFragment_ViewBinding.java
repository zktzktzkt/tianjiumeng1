// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.home.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StandardFragment_ViewBinding implements Unbinder {
  private StandardFragment target;

  @UiThread
  public StandardFragment_ViewBinding(StandardFragment target, View source) {
    this.target = target;

    target.recyclerRecommond = Utils.findRequiredViewAsType(source, R.id.recycler_recommond, "field 'recyclerRecommond'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StandardFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerRecommond = null;
  }
}
