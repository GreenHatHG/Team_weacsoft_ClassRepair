package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

/**
 * 订单表
 * status:
 * 0-异常订单 1-待处理 2-处理中 3-已处理 4-已取消
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractBasicBean{

    @Column(nullable = false)
    private int userId;

    /**
     * 订单id
     */
    @Column(nullable = false, unique = true)
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

    /**
     * 维护人员id
     * 当遇到取消接单，取消订单等操作时，重置为0，减少未来设计工作量统计时的难度
     */
    private int repairId = 0;

}
