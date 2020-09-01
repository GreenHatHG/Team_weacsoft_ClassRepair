DROP TABLE IF EXISTS material_type;
CREATE TABLE material_type
(
    id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '材料类型主键ID',
    name VARCHAR(50) COMMENT '分类名',
    sort INTEGER COMMENT '排序',
    tree_path VARCHAR(25) COMMENT '树路径',
    `create_time` bigint(20) unsigned NOT NULL COMMENT '创建时间戳',
    `delete_time` bigint(20) unsigned NOT NULL COMMENT '删除时间戳，软删除',
    `state` tinyint(4) NOT NULL COMMENT '一般1为启用，0为停用，-1为删除,特殊情况除外',
    `update_time` bigint(20) unsigned NOT NULL COMMENT '更新时间戳'
) COMMENT='备件材料分类';

ALTER TABLE material CHANGE material_type_id material_type_id INTEGER UNSIGNED NOT NULL COMMENT '材料类型id';

ALTER TABLE repair_item CHANGE state `state` tinyint(4) NOT NULL COMMENT '订单状态：1待处理，2已查看，3处理中，4已处理，5已取消'
