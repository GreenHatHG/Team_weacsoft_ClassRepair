package team.weacsoft.answer.domain;

import lombok.*;
import team.weacsoft.db.domain.basic.BaseDo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 故障详情表
 * @author GreenHatHG
 **/

@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qa_answer")
public class QaAnswerDo extends BaseDo {

    /**
     * 目录id，与qa_type表对应
     */
    @Builder.Default
    @Column(nullable = false)
    private int qaTypeId = 0;

    /**
     * 顺序，在同一目录时显示的先后顺序
     */
    @Builder.Default
    @Column(nullable = false)
    private int sort = 0;

    /**
     * 问题
     */
    @Builder.Default
    @Column(nullable = false)
    private String question = "";

    /**
     * 解决方案公开版
     */
    @Lob
    @Column(nullable = false)
    @Builder.Default
    private String answer = "";

    /**
     * 解决方案内部版
     */
    @Column(nullable = false)
    @Lob
    @Builder.Default
    private String answerRepair = "";

    /**
     * 采纳次数
     */
    @Builder.Default
    @Column(nullable = false)
    private int goodNum = 0;

}