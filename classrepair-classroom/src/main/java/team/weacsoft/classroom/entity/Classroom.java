package team.weacsoft.classroom.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课室表
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class Classroom extends BaseEntity {

    /**
     * 建筑物
     */
    private String build;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 课室号
     */
    private String room;

}
