package com.wutong.weixin.dao;

import com.wutong.weixin.dao.base.CrudDao;
import com.wutong.weixin.entity.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Author：Tom
 * Date：2017/11/1
 * Email：bibi8086@gmail.com
 */
@Repository
public interface FileDao extends CrudDao<File, Long> {

    /**
     * @param id 文件资源id
     * @return 文件具体的路径
     */
    String queryFilePathForPk(Long id);

    /**
     * 通过md5和sha1查找文件
     *
     * @param md5
     * @param sha1
     * @return
     */
    File queryByMd5AndSha1(@Param("md5") String md5, @Param("sha1") String sha1);

}
