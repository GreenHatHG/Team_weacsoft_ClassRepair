package team.weacsoft.qa.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 故障详情表
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
public class QaAnswer extends BaseEntity {

    /**
     * 对应qa_type表的id
     */
    private Integer qaTypeId;

    /**
     *  顺序，在同一目录时显示的先后顺序
     */
    private Integer sort;

    /**
     * 故障问题
     */
    private String question;

    /**
     * 解决方案公开版
     */
    @Builder.Default
    private String answerPublic = "";

    /**
     * 解决方案内部版
     */
    @Builder.Default
    private String answerPrivate = "";

    /**
     * 采纳次数
     */
    @Builder.Default
    private Integer goodNum = 0;


}
