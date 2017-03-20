// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImmediateAppraiseActivity_ViewBinding implements Unbinder {
  private ImmediateAppraiseActivity target;

  private View view2131624111;

  @UiThread
  public ImmediateAppraiseActivity_ViewBinding(ImmediateAppraiseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImmediateAppraiseActivity_ViewBinding(final ImmediateAppraiseActivity target,
      View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.ratingbar = Utils.findRequiredViewAsType(source, R.id.ratingbar, "field 'ratingbar'", RatingBar.class);
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'btnSubmit' and method 'onClick'");
    target.btnSubmit = Utils.castView(view, R.id.btn_submit, "field 'btnSubmit'", Button.class);
    view2131624111 = view;
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
    ImmediateAppraiseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.ratingbar = null;
    target.btnSubmit = null;

    view2131624111.setOnClickListener(null);
    view2131624111 = null;
  }
}
