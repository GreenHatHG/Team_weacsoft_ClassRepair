package team.weacsoft.repair.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import team.weacsoft.common.converter.DateConverter;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description
 * @ClassName RepariItem2
 * @Author CHENRUI
 * @date 2020.02.24 18:28
 */
public class RepairItem2 extends BaseEntity {

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */

    @ExcelProperty(value = "订单号")
    private String repairItemId;

    /**
     *  接单人(表id)
     */
    @ExcelProperty(value = "接单人姓名")
    private String receiverName = "-";

    /**
     *  报修人(表id)
     */
    @ExcelProperty(value = "报修人姓名")
    private String ordererName;

    /**
     * 课室
     */
    @ExcelProperty(value = "课室id")
    @NotBlank
    @Size(max = 100)
    private String classroom;

    /**
     * 故障设备
     */
    @ExcelProperty(value = "故障设备")
    @NotNull
    private String title;

    /**
     * 问题描述
     */
    @ExcelProperty(value = "问题描述")
    @NotBlank
    @Size(max = 300)
    private String problem;

    /**
     * 报修人手机号
     */
    @ExcelProperty(value = "报修人手机号")
    private String ordererPhone = "";

}
