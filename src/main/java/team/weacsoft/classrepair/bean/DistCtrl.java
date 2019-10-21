package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 字典表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "dist_ctrl")
public class DistCtrl extends BasicBean {

    /**
     * 课室id
     */
    @Column(nullable = false, unique = true)
    private int distId;

    /**
     * 键
     */
    private String distKey;

    /**
     * longtext属性
     */
    @Lob
    private String distValue;
}
