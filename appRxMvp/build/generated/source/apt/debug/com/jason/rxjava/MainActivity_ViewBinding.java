// Generated code from Butter Knife. Do not modify!
package com.jason.rxjava;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131427418;

  private View view2131427419;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mSwipeLayout = Utils.findRequiredViewAsType(source, R.id.swipe_layout, "field 'mSwipeLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_change, "field 'mBtnChange' and method 'onClick'");
    target.mBtnChange = Utils.castView(view, R.id.btn_change, "field 'mBtnChange'", Button.class);
    view2131427418 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.jump, "field 'mJump' and method 'click'");
    target.mJump = Utils.castView(view, R.id.jump, "field 'mJump'", Button.class);
    view2131427419 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.click();
      }
    });
    target.mChangeTv = Utils.findRequiredViewAsType(source, R.id.change_tv, "field 'mChangeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSwipeLayout = null;
    target.mBtnChange = null;
    target.mJump = null;
    target.mChangeTv = null;

    view2131427418.setOnClickListener(null);
    view2131427418 = null;
    view2131427419.setOnClickListener(null);
    view2131427419 = null;
  }
}
