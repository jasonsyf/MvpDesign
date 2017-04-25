package com.jason.rxjava;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.rxjava.logger.LogLevel;
import com.jason.rxjava.logger.Logger;
import com.jason.rxjava.mvpview.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.btn_change)
    Button mBtnChange;
    @BindView(R.id.jump)
    Button mJump;
    @BindView(R.id.change_tv)
    TextView mChangeTv;
    private CheckBox mCheckBox;
    private Handler mHandler =new Handler(Looper.getMainLooper());
    List<String> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        mHandler.post(() -> {
        });

        mHandler.postDelayed(() -> {

        },1000);



    }

    private void init() {
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
        mSwipeLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mChangeTv.setText("正在刷新");
                mSwipeLayout.setRefreshing(true);
                new Handler().postDelayed(() -> {
                    mChangeTv.setText("我是刷新后的数据");
                    mSwipeLayout.setRefreshing(false);
                }, 6000);
            }
        });
    }

    @OnClick(R.id.btn_change)
    public void onClick() {
        changeView();  //改变TextView的内容，初始值为
    }

    private void changeView() {
        Observable.create((ObservableOnSubscribe<String>) e -> {
            for (int i = 0; i < 5; i++) {
                e.onNext(i + "");
            }
            e.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.i("onSubscribe", d.toString());
                    }

                    @Override
                    public void onNext(String value) {
                        mChangeTv.setText(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "更换内容失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(MainActivity.this, "更换内容成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.jump)
    public void click() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        TestFragment testFragment = new TestFragment();
        transaction.add(android.R.id.content,testFragment, "tag");
        transaction.addToBackStack("tag");
        transaction.commit();
    }
}
