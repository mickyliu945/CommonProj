package com.micky.commonproj.domain;

import android.content.Context;

import com.micky.commonproj.domain.db.DBCore;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain
 *
 * @Description Domain 相关初始化
 *
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 14:34
 * @Version 1.0
 */
public class DomainInit {

    public static void init(Context context) {
        initDatabase(context);
    }

    /**
     * 初始化数据库
     * @param context
     */
    public static void initDatabase(Context context) {
        DBCore.init(context);
        DBCore.enableQueryBuilderLog();
    }
}
