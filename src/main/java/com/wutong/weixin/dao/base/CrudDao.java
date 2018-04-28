package com.wutong.weixin.dao.base;


import com.wutong.weixin.entity.Entity;

import java.io.Serializable;

/**
 *
 * 通用增删改查DAO接口,定义了增删改查.以及动态条件查询,修改,删除。
 * @param <E> PO类型
 * @param <PK> 主键类型
 * @see InsertDao
 * @see DeleteDao
 * @see UpdateDao
 * @see QueryDao
 */
public interface CrudDao<E extends Entity, PK extends Serializable> extends
        InsertDao<E>,
        DeleteDao<E, PK>,
        UpdateDao<E>,
        QueryDao<E, PK>
{
}
