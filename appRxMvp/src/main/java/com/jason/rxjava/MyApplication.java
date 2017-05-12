package com.jason.rxjava;

import android.app.Application;
import android.content.Context;

import com.jason.rxjava.logger.LogLevel;
import com.jason.rxjava.logger.Logger;
import com.jason.appTestTinker.BuildConfig;

/**
 * Created by jason_syf on 2017/2/20.
 * Email: jason_sunyf@163.com
 */

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        initLogger();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }
}
