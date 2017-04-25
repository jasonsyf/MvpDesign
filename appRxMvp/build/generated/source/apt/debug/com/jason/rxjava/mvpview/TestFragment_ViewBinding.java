// Generated code from Butter Knife. Do not modify!
package com.jason.rxjava.mvpview;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jason.rxjava.R;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestFragment_ViewBinding implements Unbinder {
  private TestFragment target;

  @UiThread
  public TestFragment_ViewBinding(TestFragment target, View source) {
    this.target = target;

    target.mName = Utils.findRequiredViewAsType(source, R.id.name, "field 'mName'", TextView.class);
    target.mAge = Utils.findRequiredViewAsType(source, R.id.age, "field 'mAge'", TextView.class);
    target.mHobby = Utils.findRequiredViewAsType(source, R.id.hobby, "field 'mHobby'", TextView.class);
    target.mListView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'mListView'", ListView.class);
    target.mRcvLoadMore = Utils.findOptionalViewAsType(source, R.id.rcv_load_more, "field 'mRcvLoadMore'", ProgressWheel.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mName = null;
    target.mAge = null;
    target.mHobby = null;
    target.mListView = null;
    target.mRcvLoadMore = null;
  }
}
