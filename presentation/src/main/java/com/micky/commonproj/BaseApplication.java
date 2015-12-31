package com.micky.commonproj;

import android.app.Application;
import android.content.Context;

import com.micky.commonlib.utils.CrashHandler;

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
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(this);
    }

    public static final Context getContext() {
        return mContext;
    }
}
