package team.weacsoft.classroom.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.db.domain.basic.BaseDo;


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
public class ClassroomDo extends BaseDo {

    /**
     * 已经生成好的，固定死的
     */
    @Column(nullable = false)
    private int classId;

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
