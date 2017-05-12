package com.jason.rxjava.mvppresenter;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.jason.rxjava.mvpcontract.TestRxContract;
import com.jason.rxjava.mvpmodel.TestModel;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by jason_syf on 2017/2/23.
 * Email: jason_sunyf@163.com
 */

public class TestRxPresenter implements TestRxContract.Presenter {
   private TestRxContract.View view;
    @NonNull
    private CompositeDisposable mSubscriptions;

    public TestRxPresenter(TestRxContract.View view) {
        this.view = view;
        mSubscriptions = new CompositeDisposable();
        view.setPresenter(this);
    }

    @Override
    public void loadTestInfo() {
        String userId =view.loadTestId();
        view.showLoading();//接口请求前显示loading
        //这里模拟接口请求回调-
        new Handler().postDelayed(() -> {
            //模拟接口返回的json，并转换为javaBean
            TestModel testModel = new TestModel("小宝", 22, "王者荣耀");
            view.showTestInfo(testModel);
            view.dismissLoading();
        }, 3000);
    }

    @Override
    public void subscribe() {
        loadTestInfo();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
