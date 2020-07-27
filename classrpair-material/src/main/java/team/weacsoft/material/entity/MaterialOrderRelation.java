package team.weacsoft.material.entity;

import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class MaterialOrderRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 材料id号
     */
    private Long materialId;

    /**
     * 订单id号
     */
    private Long orderId;

    /**
     * 使用数量
     */
    private Integer amount;


}
