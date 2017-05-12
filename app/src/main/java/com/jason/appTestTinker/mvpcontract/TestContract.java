package com.jason.appTestTinker.mvpcontract;

import com.jason.appTestTinker.mvpmodel.TestModel;
import com.jason.appTestTinker.mvppresenter.BasePresenter;
import com.jason.appTestTinker.mvpview.BaseView;

/**
 * Created by jason_syf on 2017/2/22.
 * Email: jason_sunyf@163.com
 */

public interface TestContract {
    interface Presenter extends BasePresenter {
        void loadTestInfo();
    }

    interface View extends BaseView<Presenter> {
        void showLoading();//展示加载框
        void dismissLoading();//取消加载框展示
        void showTestInfo(TestModel testModel);//将网络请求得到的用户信息回调
        String loadTestId();//假设接口请求需要一个userId
    }
}
