package com.micky.commonproj.domain.db.manager;

import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.db.manager
 *
 * @Description
 *
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-29 14:31
 * @Version 1.0
 */
public class BaseDaoManager <T, K> {
    protected AbstractDao<T, K> mDao;

    @SuppressWarnings("unchecked")
    public BaseDaoManager(AbstractDao dao) {
        mDao = dao;
    }

    /**
     * 保存对象
     * @param item
     */
    public void save(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insert(item);
    }

    /**
     * 保存可变参数对象数组
     * @param items
     */
    @SuppressWarnings("unchecked")
    public void save(T... items) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insertInTx(items);
    }

    /**
     * 保存对象List
     * @param list
     */
    public void save(List<T> list) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insertInTx(list);
    }

    /**
     * 保存或更新对象
     * @param item
     */
    public void saveOrUpdate(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insertOrReplace(item);
    }

    /**
     * 保存或更新对象数组
     * @param items
     */
    @SuppressWarnings("unchecked")
    public void saveOrUpdate(T... items) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * 保存或更新对象list
     * @param list
     */
    public void saveOrUpdate(List<T> list) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.insertOrReplaceInTx(list);
    }

    /**
     * 根据key删除
     * @param key
     */
    public void deleteByKey(K key) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.deleteByKey(key);
    }

    /**
     * 根据对象item
     * @param item
     */
    public void delete(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.delete(item);
    }

    /**
     * 删除可变数组中的对象
     * @param items
     */
    public void delete(T... items) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.deleteInTx(items);
    }

    /**
     * 删除list中的对象
     * @param list
     */
    public void delete(List<T> list) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.deleteInTx(list);
    }

    /**
     * 删除表中的所有数据
     */
    public void deleteAll() {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.deleteAll();
    }

    /**
     * 更新对象item
     * @param item
     */
    public void update(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.update(item);
    }

    /**
     * 更新可变数组中的对象
     * @param items
     */
    public void update(T... items) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.updateInTx(items);
    }

    /**
     * 更新list中的对象
     * @param list
     */
    public void update(List<T> list) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.updateInTx(list);
    }

    /**
     * 根据主键查询对象
     * @param key
     * @return
     */
    public T query(K key) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        return mDao.load(key);
    }

    /**
     * 查询表中的所有对象
     * @return
     */
    public  List<T> queryAll() {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        return mDao.loadAll();
    }

    /**
     * 根据条件查询
     * @param where 条件
     * @param params 条件whree所需要的参数
     * @return
     */
    public List<T> query(String where, String... params) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        return mDao.queryRaw(where, params);
    }

    /**
     * 获取QueryBuilder
     * @return
     */
    public QueryBuilder<T> queryBuilder() {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        return mDao.queryBuilder();
    }

    /**
     * 返回表中所有数据的记录数
     * @return
     */
    public long count() {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        return mDao.count();
    }

    public void refresh(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.refresh(item);
    }

    public void detach(T item) {
        if (mDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        mDao.detach(item);
    }
}
