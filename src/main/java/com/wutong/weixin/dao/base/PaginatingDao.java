package com.wutong.weixin.dao.base;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaginatingDao<T> {


    /**
     *
     * @param condition 查询条件
     * @param sorting 排序字段
     * @param lineNo 起始行数
     * @param pageSize 查询的行数
     * @return
     */
    List<T> pagingQuery(@Param("condition") Map<String, Object> condition,
                        @Param("sorting") Map<String, String> sorting,
                        @Param("lineNo") Integer lineNo,
                        @Param("pageSize") Integer pageSize);

    /**
     *
     * @param condition 查询条件
     * @return 条数
     */
    int total(@Param("condition") Map<String, Object> condition);



}
