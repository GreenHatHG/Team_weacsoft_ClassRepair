package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 问题目录表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "qa_type")
public class QaType extends AbstractBasicBean{

    /**
     * 顺序，显示的先后顺序
     */
    @Column(nullable = false)
    private int sort;

    /**
     * 显示的标题
     */
    @Column(nullable = false)
    private String title;

    /**
     * 备注
     */
    private String remark;
}
