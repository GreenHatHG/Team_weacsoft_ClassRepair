package team.weacsoft.orders.entity;

import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 报修表
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepairItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */
    private String repairItemId;

    /**
     *  接单人(表id)
     */
    private Integer receiver;

    /**
     *  报修人(表id)
     */
    private Integer orderer;

    /**
     * 课室
     */
    private String classroom;

    /**
     * 故障设备
     */
    private String equipmentType;

    /**
     * 问题描述
     */
    private String problem;

    /**
     * 报修人手机号
     */
    private String ordererPhone;

    /**
     * 是否是紧急订单
     */
    private Boolean urgent;

    /**
     * 订单及时性，1为及时，0为未填，-1为不及时
     */
    private Integer onTime;

    /**
     * 评价内容，文本
     */
    private String appraisal;

    /**
     * 5个星级，分10个等级
     */
    private Integer star;


}
