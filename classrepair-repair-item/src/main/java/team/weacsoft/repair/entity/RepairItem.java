package team.weacsoft.repair.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 报修表
 * state: 1待处理，2已查看，3处理中，4已处理，5已取消
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepairItem extends BaseEntity {

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */
    @ExcelProperty(value = "")
    private String repairItemId;

    /**
     *  接单人(表id)
     */
    private Integer receiver = 0;

    /**
     *  报修人(表id)
     */
    private Integer orderer;

    /**
     * 课室
     */
    @NotBlank
    @Size(max = 100)
    private String classroom;

    /**
     * 故障设备
     */
    @NotNull
    private Integer equipmentType;

    /**
     * 问题描述
     */
    @NotBlank
    @Size(max = 300)
    private String problem;

    /**
     * 报修人手机号
     */
    private String ordererPhone = "";

    /**
     * 是否是紧急订单
     */
    private Boolean urgent = false;

    /**
     * 是否及时
     */
    private Integer onTime = 0;

    /**
     * 用户评价
     */
    private String appraisal = "用户未评价";

    /**
     * 星评
     */
    private Integer star = 8;
}
