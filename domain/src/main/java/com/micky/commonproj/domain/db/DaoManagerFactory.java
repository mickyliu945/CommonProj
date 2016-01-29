package com.micky.commonproj.domain.db;

import com.micky.commonproj.domain.db.dao.PlaceDao;
import com.micky.commonproj.domain.db.manager.PlaceDaoManager;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.db
 *
 * @Description
 *
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 14:29
 * @Version 1.0
 */
public class DaoManagerFactory {

    private static PlaceDaoManager sPlaceDaoManager;

    public static PlaceDaoManager getPlaceDaoManager() {
        if (sPlaceDaoManager == null) {
            PlaceDao userDao = DBCore.getDaoSession().getPlaceDao();
            sPlaceDaoManager = new PlaceDaoManager(userDao);
        }
        return sPlaceDaoManager;
    }

}
