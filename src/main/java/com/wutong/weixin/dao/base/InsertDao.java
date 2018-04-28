package com.wutong.weixin.dao.base;


import com.wutong.weixin.entity.Entity;

/**
 *
 * 通用数据插入DAO接口
 * @param <E> 实体类型
 */
public interface InsertDao<E extends Entity> extends Dao {
    /**
     * 新增数据
     * @param e 实体对象
     * @return 新增记录行数
     */
    int insert(E e);
}
