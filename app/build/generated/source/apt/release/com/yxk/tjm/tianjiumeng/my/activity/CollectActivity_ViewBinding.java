// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.MyToolbar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CollectActivity_ViewBinding implements Unbinder {
  private CollectActivity target;

  @UiThread
  public CollectActivity_ViewBinding(CollectActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CollectActivity_ViewBinding(CollectActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", MyToolbar.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
    target.cbAll = Utils.findRequiredViewAsType(source, R.id.cb_all, "field 'cbAll'", CheckBox.class);
    target.btnDelete = Utils.findRequiredViewAsType(source, R.id.btn_delete, "field 'btnDelete'", Button.class);
    target.relativeBottom = Utils.findRequiredViewAsType(source, R.id.relative_bottom, "field 'relativeBottom'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CollectActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recycler = null;
    target.cbAll = null;
    target.btnDelete = null;
    target.relativeBottom = null;
  }
}
