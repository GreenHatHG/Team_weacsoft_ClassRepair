package team.weacsoft.material.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import team.weacsoft.common.persistence.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MaterialType extends BaseEntity {

    /**
     * 分类名
     */
    @NotBlank
    @Size(max = 20)
    private String name;

    /**
     * 排序
     */
    private Integer sort = 1;

    /**
     * 树路径——可以忽略
     */
    private String treePath="";

}
