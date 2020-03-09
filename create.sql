drop table user_feedback;
create table user_feedback(
    id mediumint(5) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    create_time bigint(20) UNSIGNED NOT NULL COMMENT '创建日期',
    update_time bigint(20) UNSIGNED NOT NULL COMMENT '修改日期',
    delete_time bigint(20) UNSIGNED NOT NULL COMMENT '删除日期',
    state tinyint(4) UNSIGNED NOT NULL COMMENT '状态',
    orderer mediumint(5) UNSIGNED NOT NULL COMMENT '反馈人',
    reveicer mediumint(5) UNSIGNED NOT NULL COMMENT '处理人',
    question varchar(255) NOT NULL COMMENT '问题描述',
    orderer_phone varchar(255) NOT NULL COMMENT '反馈人电话',
    primary key(id)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户反馈表' ROW_FORMAT = Dynamic;
