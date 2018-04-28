package com.wutong.weixin.dao.base;


import com.wutong.weixin.entity.Entity;

/**
 *
 * 通用更新dao
 * @param <E> 实体类型
 */
public interface UpdateDao<E extends Entity> extends Dao {
    /**
     * 根据实体类进行更新,实体类支持动态条件或者普通实体类。
     * @param e 实体对象
     * @return
     */
    int update(E e);
}
