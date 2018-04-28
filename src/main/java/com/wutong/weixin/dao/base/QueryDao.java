package com.wutong.weixin.dao.base;



import com.wutong.weixin.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 通用查询dao
 * @param <E> 实体类型
 * @param <PK> 主键类型
 */
public interface QueryDao<E extends Entity, PK extends Serializable> extends Dao {

    /**
     * 根据实体类动态查询，可传入特定的实体类进行查询。
     * @param e 实体对象
     * @return
     */
    List<E> query(E e);

    /**
     * 根据实体类动态查询 数量
     * @param e 实体对象
     * @return
     */
    Long count(E e);

    /**
     * 根据主键查询对象
     *
     * @param pk    主键
     * @return
     */
    E queryByPk(PK pk);


}
