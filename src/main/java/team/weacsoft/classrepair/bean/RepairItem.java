package team.weacsoft.classrepair.bean;

import lombok.*;
import team.weacsoft.classrepair.bean.basic.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 报修表
 * status:
 * 0-异常订单 1-待处理 2-处理中 3-已处理 4-已取消
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_item")
public class RepairItem extends BaseBean {

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字
     */
    @Builder.Default
    @Column(nullable = false, unique = true)
    private String repairItemId = "";

    /**
     * 接单人ID，用户工号/学号
     */
    @Builder.Default
    @Column(nullable = false)
    private String receiverUserId = "";

    /**
     * 报修人
     */
    @Builder.Default
    @Column(nullable = false)
    private String orderUserId = "";

    /**
     * 课室
     */
    @Builder.Default
    @Column(nullable = false)
    private String classroom = "";

    /**
     * 故障设备
     */
    @Builder.Default
    @Column(nullable = false)
    private String equipmentType = "";

    /**
     * 问题描述
     */
    @Builder.Default
    @Column(nullable = false)
    private String problem = "";

    /**
     * 报修人手机号
     */
    @Builder.Default
    @Column(nullable = false)
    private String oderUserPhone = "";

}
