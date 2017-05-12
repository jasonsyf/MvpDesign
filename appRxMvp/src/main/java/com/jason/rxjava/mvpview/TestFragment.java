package com.jason.rxjava.mvpview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.rxjava.adapter.BaseViewHolder;
import com.jason.rxjava.logger.Logger;
import com.jason.rxjava.mvpmodel.TestModel;
import com.jason.rxjava.mvppresenter.TestRxPresenter;
import com.jason.rxjava.adapter.CommonAdapter;
import com.jason.appTestTinker.R;
import com.jason.rxjava.mvpcontract.TestRxContract;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements TestRxContract.View {


    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.age)
    TextView mAge;
    @BindView(R.id.hobby)
    TextView mHobby;
    @BindView(R.id.listView)
    ListView mListView;
    @Nullable
    @BindView(R.id.rcv_load_more)
    ProgressWheel mRcvLoadMore;
    private CommonAdapter<String> mCommonAdapter;
    private TestRxContract.Presenter mPresenter;
//
    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        new TestRxPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void showLoading() {
        Toast.makeText(getContext(), "正在加载", Toast.LENGTH_LONG).show();
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.progress_bar, null);
        mListView.addHeaderView(view);
        mCommonAdapter = new CommonAdapter<String>(getContext(), null, android.R.layout.test_list_item) {
            @Override
            protected void setListeners(BaseViewHolder holder, View view, int position) {

            }

            @Override
            protected void setViewDimen(View convertView) {

            }

            @Override
            protected void setViewData(int position, BaseViewHolder holder, String item) {
                holder.setText(android.R.id.text1, item);
            }

            @Override
            public void onClickBack(int position, View view, BaseViewHolder holder) {

            }
        };
        mListView.setAdapter(mCommonAdapter);
        mCommonAdapter.update(strings);
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTestInfo(TestModel testModel) {
        Logger.i("model", testModel.getAge() + testModel.getHobby() + testModel.getName());
        if (testModel != null) {
            mName.setText(testModel.getName());
            mAge.setText(String.valueOf(testModel.getAge()));
            mHobby.setText(testModel.getHobby());
        }
    }

    @Override
    public String loadTestId() {
        return null;
    }


    @Override
    public void setPresenter(TestRxContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
