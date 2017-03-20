// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.custom;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SpecialRecommendLayout_ViewBinding implements Unbinder {
  private SpecialRecommendLayout target;

  private View view2131624292;

  private View view2131624293;

  private View view2131624294;

  private View view2131624291;

  @UiThread
  public SpecialRecommendLayout_ViewBinding(SpecialRecommendLayout target) {
    this(target, target);
  }

  @UiThread
  public SpecialRecommendLayout_ViewBinding(final SpecialRecommendLayout target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_pic_1, "field 'ivPic1' and method 'onClick'");
    target.ivPic1 = Utils.castView(view, R.id.iv_pic_1, "field 'ivPic1'", ImageView.class);
    view2131624292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pic_2, "field 'ivPic2' and method 'onClick'");
    target.ivPic2 = Utils.castView(view, R.id.iv_pic_2, "field 'ivPic2'", ImageView.class);
    view2131624293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pic_3, "field 'ivPic3' and method 'onClick'");
    target.ivPic3 = Utils.castView(view, R.id.iv_pic_3, "field 'ivPic3'", ImageView.class);
    view2131624294 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_0, "method 'onClick'");
    view2131624291 = view;
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
    SpecialRecommendLayout target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivPic1 = null;
    target.ivPic2 = null;
    target.ivPic3 = null;

    view2131624292.setOnClickListener(null);
    view2131624292 = null;
    view2131624293.setOnClickListener(null);
    view2131624293 = null;
    view2131624294.setOnClickListener(null);
    view2131624294 = null;
    view2131624291.setOnClickListener(null);
    view2131624291 = null;
  }
}
