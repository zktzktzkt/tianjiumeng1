// Generated code from Butter Knife. Do not modify!
package com.yxk.tjm.tianjiumeng.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.yxk.tjm.tianjiumeng.R;
import com.yxk.tjm.tianjiumeng.custom.SpecialRecommendLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.specialOne = Utils.findRequiredViewAsType(source, R.id.special_one, "field 'specialOne'", SpecialRecommendLayout.class);
    target.specialTwo = Utils.findRequiredViewAsType(source, R.id.special_two, "field 'specialTwo'", SpecialRecommendLayout.class);
    target.specialThree = Utils.findRequiredViewAsType(source, R.id.special_three, "field 'specialThree'", SpecialRecommendLayout.class);
    target.rlSearch = Utils.findRequiredViewAsType(source, R.id.rl_search, "field 'rlSearch'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.specialOne = null;
    target.specialTwo = null;
    target.specialThree = null;
    target.rlSearch = null;
  }
}
