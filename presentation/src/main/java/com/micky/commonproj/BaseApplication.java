package com.micky.commonproj;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.micky.commonlib.utils.Constants;
import com.micky.commonlib.utils.CrashHandler;
import com.micky.commonlib.utils.FileUtils;
import com.micky.commonproj.domain.DomainInit;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.apache.log4j.Level;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * @Project CommonProject
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
        DomainInit.init(this);
        mRefWatcher = Constants.DEBUG ?  LeakCanary.install(this) : RefWatcher.DISABLED;
        Fresco.initialize(this);
        initLog4j();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

    private void initLog4j() {
        final LogConfigurator logConfigurator = new LogConfigurator();

        String logPath = FileUtils.getLogFilePath();
        try {
            if (!"".equals(logPath)) {
                File file = new File(logPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                logPath += File.separator + Constants.LOG_FILE;
                file = new File(logPath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                logConfigurator.setFileName(logPath);
            }
            Level level = Constants.DEBUG ? Level.ALL : Level.ERROR;
            logConfigurator.setRootLevel(level);
            logConfigurator.setLevel("org.apache", Level.ALL);
            logConfigurator.configure();
        } catch (Exception e) {
            android.util.Log.e("Application", e.getMessage(), e);
        }
    }
}
