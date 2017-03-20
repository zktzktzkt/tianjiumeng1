// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MySettingActivity_ViewBinding implements Unbinder {
  private MySettingActivity target;

  private View view2131624138;

  private View view2131624141;

  private View view2131624142;

  @UiThread
  public MySettingActivity_ViewBinding(MySettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MySettingActivity_ViewBinding(final MySettingActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'tvCache'", TextView.class);
    view = Utils.findRequiredView(source, R.id.relative_clean_cache, "field 'relativeCleanCache' and method 'onClick'");
    target.relativeCleanCache = Utils.castView(view, R.id.relative_clean_cache, "field 'relativeCleanCache'", RelativeLayout.class);
    view2131624138 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_question, "field 'rlQuestion' and method 'onClick'");
    target.rlQuestion = Utils.castView(view, R.id.rl_question, "field 'rlQuestion'", RelativeLayout.class);
    view2131624141 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_quit, "field 'rlQuit' and method 'onClick'");
    target.rlQuit = Utils.castView(view, R.id.rl_quit, "field 'rlQuit'", RelativeLayout.class);
    view2131624142 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MySettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.tvCache = null;
    target.relativeCleanCache = null;
    target.rlQuestion = null;
    target.rlQuit = null;

    view2131624138.setOnClickListener(null);
    view2131624138 = null;
    view2131624141.setOnClickListener(null);
    view2131624141 = null;
    view2131624142.setOnClickListener(null);
    view2131624142 = null;
  }
}
