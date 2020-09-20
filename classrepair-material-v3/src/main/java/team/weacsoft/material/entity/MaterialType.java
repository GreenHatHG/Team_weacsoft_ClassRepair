package team.weacsoft.material.entity;

import lombok.Data;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Description 材料的大类
 * @Author 魔法はまだ解けない
 * @Date 2020/9/10
 */
@Data
public class MaterialType extends BaseEntity {
    /**
     * 材料名
     */
    @NotBlank
    private String name;

    /**
     * 排序方式
     */
    private Integer sort = 1;

    /**
     * 材料种类，1为大项，2为小项
     */
    private Integer type = 1;

    /**
     * 父id
     * 本类为空
     */
    private Integer fid=0;

    /**
     * 修改人id
     */
    private Integer userid;
}
