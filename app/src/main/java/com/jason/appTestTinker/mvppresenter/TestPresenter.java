package com.jason.appTestTinker.mvppresenter;

import android.os.Handler;

import com.jason.appTestTinker.mvpcontract.TestContract;
import com.jason.appTestTinker.mvpmodel.TestModel;

/**
 * Created by jason_syf on 2017/2/22.
 * Email: jason_sunyf@163.com
 */

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View view;

    public TestPresenter(TestContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }
    @Override
    public void loadTestInfo() {
        String userId = view.loadTestId();
        view.showLoading();//接口请求前显示loading
        //这里模拟接口请求回调-
        new Handler().postDelayed(() -> {
            //模拟接口返回的json，并转换为javaBean
            TestModel testModel = new TestModel("小宝", 22, "王者荣耀");
            view.showTestInfo(testModel);
            view.dismissLoading();
        },3000);
    }

    @Override
    public void start() {
        loadTestInfo();
    }
}
