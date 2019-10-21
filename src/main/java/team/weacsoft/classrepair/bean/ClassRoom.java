package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 课室表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "class_room")
public class ClassRoom extends BasicBean {

    @Column(nullable = false, unique = true)
    private String classId;

    /**
     * 建筑物
     */
    @Column(nullable = false)
    private String build;

    /**
     * 楼层
     */
    @Column(nullable = false)
    private String floor;

    /**
     * 课室号
     */
    @Column(nullable = false)
    private String room;
}
