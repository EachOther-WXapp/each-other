-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `each_other` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `each_other`;

CREATE TABLE IF NOT EXISTS `user`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `username`            VARCHAR(20) COMMENT '用户名',
  `password`            VARCHAR(128)  COMMENT '密码（md5加密）',
  `nick_name`           VARCHAR(32)  COMMENT '昵称',
  `gender`              TINYINT  COMMENT '性别 值为1时是男性，值为2时是女性，值为0时是未知',
  `avatar_url`          VARCHAR(256)  COMMENT '用户微信头像url',
  `city`                VARCHAR(32)  COMMENT '用户所在城市',
  `province`            VARCHAR(32)  COMMENT '用户所在省份',
  `country`             VARCHAR(32)  COMMENT '用户所在国家',
  `openId`              VARCHAR(128)  COMMENT '微信的openid',
  `session_key`         VARCHAR(128)  COMMENT '微信的session_key',
  `union_id`            VARCHAR(128)  COMMENT '微信的UnionID',
  `token`               VARCHAR(64)  COMMENT '用户登录的token',
  `expire_time`         datetime COMMENT '用户登录token的过期时间',
  `status`              TINYINT DEFAULT 1 COMMENT '账号状态（-1：禁用，0：正常，1：撤销）',
  PRIMARY KEY (`id`),
  KEY `idx_username` (`username`),
  KEY `idx_openid` (`openId`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `train`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `user_id`             BIGINT NOT NULL COMMENT '创建者id',
  `train_start_time`    datetime NOT NULL COMMENT '培训开始时间',
  `train_end_time`      datetime NOT NULL COMMENT '培训结束时间',
  `theme`               VARCHAR(32) not null COMMENT '主题',
  `lecturer`            VARCHAR(32) not null COMMENT '讲师名字',
  `site`                VARCHAR(64)  COMMENT '培训地点',
  `detail`              text COMMENT '培训详情',
  `conference_id`       VARCHAR(32)  COMMENT 'TeamView的房间号',
  `github_url`          VARCHAR(64)  COMMENT '上传github的资料地址',
  `like_number`         int DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '培训信息表';

CREATE TABLE IF NOT EXISTS `user_train`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `user_id`             BIGINT NOT NULL COMMENT '用户id',
  `train_id`            BIGINT NOT NULL COMMENT '培训id',
  PRIMARY KEY (`id`),
  unique key (`user_id`, `train_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '用户加入的培训表';



CREATE TABLE IF NOT EXISTS `comment`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `user_id`             BIGINT NOT NULL COMMENT '评论者id',
  `train_id`            BIGINT COMMENT '培训信息id',
  `comment_id`          BIGINT COMMENT '评论id',
  `content`             VARCHAR(32) not null COMMENT '评论内容',
  `like_number`         int DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '培训评论表';

CREATE TABLE IF NOT EXISTS `vote`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `status`              TINYINT DEFAULT 0 COMMENT '此投票组状态 0:正在投票 1:已结束投票',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '培训投票组表';

CREATE TABLE IF NOT EXISTS `vote_option`(
  `id`                  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time`         datetime DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  `modified_time`       datetime DEFAULT null ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
  `user_id`             BIGINT NOT NULL COMMENT '投票选项发起者id',
  `vote_id`             BIGINT NOT NULL COMMENT '投票组id(第一个发起者会自动创建voteId)',
  `content`             VARCHAR(32) not null COMMENT '投票选项',
  `approve_amount`      int DEFAULT 0 COMMENT '赞成数量',
  `disapprove_amount`   int DEFAULT 0 COMMENT '不赞成数量',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '培训投票选项表(一组投票最多5个)';




























