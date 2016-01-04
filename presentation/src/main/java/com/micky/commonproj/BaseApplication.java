package com.micky.commonproj;

import android.app.Application;
import android.content.Context;

import com.micky.commonlib.utils.Constants;
import com.micky.commonlib.utils.CrashHandler;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.commonproj
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-21 17:47
 * @Version 1.0
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init(this);
        mRefWatcher = Constants.DEBUG ?  LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

}
