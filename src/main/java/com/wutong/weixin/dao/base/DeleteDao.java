package com.wutong.weixin.dao.base;


import com.wutong.weixin.entity.Entity;

import java.io.Serializable;

/**
 *
 * 通用删除dao
 * @param <E> 实体类型
 * @param <PK> 主键类型
 */
public interface DeleteDao<E extends Entity, PK extends Serializable> extends Dao{

    /**
     * 根据实体类条件进行删除,删除条件根据实体类进行解析
     * @param e 实体对象
     * @return
     */
    int delete(E e);

    /**
     * 根据主键删除数据,并返回被删除数据的数量
     *
     * @param pk   主键
     * @return  删除数据的行数，理论上此返回值应该为0或者1.
     */
    int deleteByPk(PK pk);


}
