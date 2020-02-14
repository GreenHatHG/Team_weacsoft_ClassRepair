package team.weacsoft.log.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 报修表
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepairLog extends BaseEntity {

    /**
     * 维护日志
     */
    @NotBlank
    @Size(max = 10000)
    private String logContent;

    /**
     * 报修单id，后端自动生成，规则：当前日期+时间戳前十一位数字，2020012715801331743
     */
    @NotBlank
    @Size(max = 100)
    private String repairItemId;

}
