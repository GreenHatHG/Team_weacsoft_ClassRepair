package team.weacsoft.repair.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * 评价订单实体
 */
@Data
public class Evaluate {
    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */
    @NotBlank
    private String repairItemId;

    /**
     * 是否及时 -1不及时，0未填写，1及时
     */
    @NotNull
    @Max(1)
    @Min(-1)
    private Integer onTime = 0;

    /**
     * 用户评价
     */
    @NotBlank
    private String appraisal = "用户未评价";

    /**
     * 星评   1到10，默认8
     */
    @NotNull
    @Max(10)
    @Min(0)
    private Integer star = 8;

}
