package team.weacsoft.material.entity;

import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <p>
 *
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Material extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 材料名
     */
    @NotBlank
    @Size(max = 20)
    private String name;

    /**
     * 材料数量
     */
    @NotNull
    private Integer amount;

    /**
     * 材料类型
     */
    private Integer type = 1;

    /**
     * 价格
     */
    @NotNull
    private Double price;

    /**
     * 排序
     */
    private Integer sort = 1;

}
