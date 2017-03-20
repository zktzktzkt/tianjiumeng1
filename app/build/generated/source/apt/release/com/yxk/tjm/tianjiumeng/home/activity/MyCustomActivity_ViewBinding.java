// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.home.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyCustomActivity_ViewBinding implements Unbinder {
  private MyCustomActivity target;

  @UiThread
  public MyCustomActivity_ViewBinding(MyCustomActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyCustomActivity_ViewBinding(MyCustomActivity target, View source) {
    this.target = target;

    target.tvTextrue = Utils.findRequiredViewAsType(source, R.id.tv_textrue, "field 'tvTextrue'", TextView.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    target.linearTexture = Utils.findRequiredViewAsType(source, R.id.linear_texture, "field 'linearTexture'", LinearLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.recycler = Utils.findRequiredViewAsType(source, R.id.recycler, "field 'recycler'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyCustomActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTextrue = null;
    target.btnSubmit = null;
    target.linearTexture = null;
    target.toolbar = null;
    target.recycler = null;
  }
}
