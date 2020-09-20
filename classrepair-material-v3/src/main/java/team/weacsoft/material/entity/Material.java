package team.weacsoft.material.entity;

import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 材料种类及材料
 * </p>
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-09-10
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
    private String name;

    /**
     * 材料数量
     */
    private Integer amount = 0;

    /**
     * 排序
     */
    private Integer sort = 1;

    /**
     * 价格
     */
    private Double price = 0.0;

    /**
     * 材料类型，1为大项、2为小项
     */
    private Integer type = 1;

    /**
     * 父类材料id，0为其他类型材料种类
     */
    private Integer fid = 100;

    /**
     * 操作人id
     */
    private Integer userid;

}
