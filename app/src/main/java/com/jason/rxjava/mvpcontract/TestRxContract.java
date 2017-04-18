package com.jason.rxjava.mvpcontract;

import com.jason.rxjava.mvpmodel.TestModel;
import com.jason.rxjava.mvppresenter.BasePresenter;
import com.jason.rxjava.mvppresenter.BaseRXPresenter;
import com.jason.rxjava.mvpview.BaseView;

/**
 * Created by jason_syf on 2017/2/23.
 * Email: jason_sunyf@163.com
 */

public interface TestRxContract {
    interface View extends BaseView<TestRxContract.Presenter> {
        void showLoading();//展示加载框
        void dismissLoading();//取消加载框展示
        void showTestInfo(TestModel testModel);//将网络请求得到的用户信息回调
        String loadTestId();//假设接口请求需要一个userId
    }
    interface Presenter extends BaseRXPresenter {
        void loadTestInfo();
    }
}
