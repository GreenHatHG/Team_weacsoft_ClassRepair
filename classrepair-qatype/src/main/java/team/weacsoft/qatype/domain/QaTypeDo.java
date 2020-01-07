package team.weacsoft.qatype.domain;

import lombok.*;
import team.weacsoft.db.domain.basic.BaseDo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 常见故障分类表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "qa_type")
public class QaTypeDo extends BaseDo {

    /**
     * 顺序，显示的先后顺序
     */
    @Builder.Default
    @Column(nullable = false)
    private int sort = 0;

    /**
     * 显示的标题
     */
    @Builder.Default
    @Column(nullable = false)
    private String title = "";

    /**
     * 备注
     */
    @Builder.Default
    @Column(nullable = false)
    private String remark = "";
}
