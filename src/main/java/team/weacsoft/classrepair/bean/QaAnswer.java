package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 问题详情表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "qa_answer")
public class QaAnswer extends BasicBean {

    /**
     * 目录id，与qa_type表对应
     */
    @Column(nullable = false, unique = true)
    private int menuId;

    /**
     * 顺序，在同一目录时显示的先后顺序
     */
    @Column(nullable = false)
    private int sort;

    /**
     * 问题
     */
    @Column(nullable = false)
    private String question;

    /**
     * 解决方案公开版
     */
    @Lob
    private String answer;

    /**
     * 解决方案内部版
     */
    @Lob
    private String answerRepair;

    /**
     * 采纳次数
     */
    @Column(nullable = false)
    private int goodNum;


}
