package team.weacsoft.timetable.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseEntity;
import lombok.experimental.Accessors;

/**
 * 值班表
 * state: 1-签到 2-签退
 * @author GreenHatHG
 * @since 2020-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class TimeTable extends BaseEntity {
    /**
     * 用户id
     */
    private Integer userId;
}
