package team.weacsoft.qa.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import team.weacsoft.common.persistence.BaseEntity;

/**
 * <p>
 * 常见故障分类表
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
public class QaType extends BaseEntity {
    /**
     * 顺序，显示的先后顺序
     */
    private Integer sort;

    /**
     * 显示的标题
     */
    private String title;

    /**
     * 备注
     */
    @Builder.Default
    private String remark = "";

}
