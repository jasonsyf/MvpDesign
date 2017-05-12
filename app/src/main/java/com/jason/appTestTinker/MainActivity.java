package com.jason.appTestTinker;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.appTestTinker.mvpview.TestFragment;
import com.jason.appTestTinker.logger.LogLevel;
import com.jason.appTestTinker.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();



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
                    Toast.makeText(MainActivity.this, "热更完成", Toast.LENGTH_SHORT).show();
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
