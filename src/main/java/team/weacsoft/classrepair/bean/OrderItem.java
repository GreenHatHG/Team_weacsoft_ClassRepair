package team.weacsoft.classrepair.bean;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 订单表
 * status:
 * 0-异常订单 1-待处理 2-处理中 3-已处理 4-已取消
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem extends BasicBean{

    /**
     * 订单id，后端自动生成，规则：当前日期+时间戳前十一位数字
     */
    @Column(nullable = false, unique = true)
    private String orderItemId;

    /**
     * 接单人ID，用户表里面的那个主键id
     */
    private String receiverId;

    /**
     * 下单人id
     */
    private String orderId;
    /**
     * 课室
     */
    @Column(nullable = false)
    private String classroom;

    /**
     * 故障设备
     */
    @Column(nullable = false)
    private String type;

    /**
     * 问题描述
     */
    @Column(nullable = false)
    private String content;

    /**
     * 手机号
     */
    private String phone;

}
