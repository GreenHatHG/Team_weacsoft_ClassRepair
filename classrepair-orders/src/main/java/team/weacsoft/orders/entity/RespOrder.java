package team.weacsoft.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 本类包含了一个订单所需要的所有数据
 * @Author 魔法はまだ解けない
 * @Date 2020/9/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespOrder {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 订删除时间
     */
    private Long deleteTime;
    /**
     * 状态
     */
    private Integer state;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 订单号，搜索依据
     */
    private String repairItemId;

    /**
     * 接单人id
     */
    private Integer receiver;

    /**
     * 接单人名字
     */
    private String receiverName = "";


    private Long receiverIdentityId;
    /**
     * 下单人id
     */
    private Integer orderer;

    /**
     * 下单人名字
     */
    private String ordererName = "";
    /**
     * 教室号
     */
    private String classroom;

    /**
     * 问题描述
     */
    private String problem;


    /**
     * 下单人电话
     */
    private String ordererPhone = "";

    /**
     * 接单人电话
     */
    private String receiverPhone = "";

    /**
     * 故障分类
     */
    private String title;

    /**
     * 是否及时
     */
    private Integer onTime;

    /**
     * 用户评价
     */

    private String appraisal;

    /**
     * 订单星级
     */
    private Integer star;
}
