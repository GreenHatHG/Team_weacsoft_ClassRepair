CREATE TABLE material
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID' PRIMARY KEY ,
    name          VARCHAR(25)         NOT NULL COMMENT '材料名',
    amount        INTEGER             NOT NULL DEFAULT 100 COMMENT '材料数量',
    type          TINYINT             NOT NULL DEFAULT 1 COMMENT '材料类型',
    price         DOUBLE UNSIGNED     NOT NULL COMMENT '价格',
    sort          TINYINT UNSIGNED    NOT NULL COMMENT '排序',
    `create_time` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间戳',
    `delete_time` bigint(20) UNSIGNED NOT NULL COMMENT '删除时间戳，软删除',
    `state`       tinyint(4)          NOT NULL DEFAULT 1 COMMENT '1-正常,2-已删除',
    `update_time` bigint(20) UNSIGNED NOT NULL COMMENT '更新时间戳'
);

CREATE TABLE material_order_relation
(
    `id`          bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID' PRIMARY KEY ,
    `create_time` bigint(20) UNSIGNED NOT NULL COMMENT '创建时间戳',
    `delete_time` bigint(20) UNSIGNED NOT NULL COMMENT '删除时间戳，软删除',
    `state`       tinyint(4)          NOT NULL DEFAULT 1 COMMENT '1-正常，2-已删除',
    `update_time` bigint(20) UNSIGNED NOT NULL COMMENT '更新时间戳',
    material_id BIGINT UNSIGNED NOT NULL COMMENT '材料id号',
    order_id BIGINT UNSIGNED NOT NULL COMMENT '订单id号',
    amount        INTEGER             NOT NULL DEFAULT 1 COMMENT '使用数量'
)