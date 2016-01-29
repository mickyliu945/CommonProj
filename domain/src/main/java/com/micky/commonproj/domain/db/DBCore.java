package com.micky.commonproj.domain.db;

import android.content.Context;

import com.micky.commonproj.domain.db.dao.DaoMaster;
import com.micky.commonproj.domain.db.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.db
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 14:27
 * @Version 1.0
 */
public class DBCore {
    private static final String DEFAULT_DB_NAME = "green_dao_test.db";
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;

    private static Context sContext;
    private static String sDbName;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("Init DBCore failed, context can't be null.");
        }
        sContext = context.getApplicationContext();
        sDbName = dbName;
    }

    public static DaoMaster getDaoMaster() {
        if (sDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sContext, sDbName, null);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public static DaoSession getDaoSession() {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    public static void enableQueryBuilderLog() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}