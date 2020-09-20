package team.weacsoft.common.persistence;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.converter.DateConverter;
import team.weacsoft.common.converter.StateConverter;

import java.io.Serializable;

/**
 * 实体类的基类
 * @author GreenHatHG
 */

@Data
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class BaseEntity implements Serializable {

    /**
     * 主键ID,自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 一般-1为删除,0为停用,1为启用，特殊情况除外
     */
    @ExcelProperty(value = "状态", converter = StateConverter.class)
    @Builder.Default
    private Integer state = 1;

    /**
     * 创建时间戳
     */
    @ExcelProperty(value = "创建时间", converter = DateConverter.class)
    @TableField(fill = FieldFill.INSERT)
    @JSONField(serialize=false)
    private Long createTime;

    /**
     * 更新时间戳
     */
    @ExcelProperty(value = "更新时间", converter = DateConverter.class)
    @TableField(fill = FieldFill.UPDATE)
    @JSONField(serialize=false)
    @Builder.Default
    private Long updateTime = 0L;

    /**
     * 删除时间戳，软删除
     */
    @ExcelProperty(value = "删除时间", converter = DateConverter.class)
    @Builder.Default
    @JSONField(serialize=false)
    private Long deleteTime = 0L;

}
