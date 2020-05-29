

DROP TABLE IF EXISTS `t_cmp_result`;
CREATE TABLE `t_cmp_result`
(
    `id`  int(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `service`    varchar(255)  NOT NULL COMMENT '标题',
    `container`  varchar(255) NOT NULL COMMENT '内容',
    `params`     text     NOT NULL COMMENT '参数',
    `result` text     NOT NULL   COMMENT '计算结果',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`      int(11)     NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL,
    `password` varchar(18) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8;