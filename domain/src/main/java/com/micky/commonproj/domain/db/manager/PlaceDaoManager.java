package com.micky.commonproj.domain.db.manager;

import com.micky.commonproj.domain.model.Place;

import de.greenrobot.dao.AbstractDao;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.db.manager
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 13:43
 * @Version 1.0
 */
public class PlaceDaoManager extends BaseDaoManager<Place, Long> {

    public PlaceDaoManager(AbstractDao dao) {
        super(dao);
    }
}
