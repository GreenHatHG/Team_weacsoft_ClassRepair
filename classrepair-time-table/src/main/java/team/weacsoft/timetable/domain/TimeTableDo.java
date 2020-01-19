package team.weacsoft.timetable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.db.domain.basic.BaseDo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 值班表
 * status:
 * 1-签到 2-签退
 * @author GreenHatHG
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "time_table")
public class TimeTableDo extends BaseDo {

    @Column(nullable = false)
    private String userId = "";

    public TimeTableDo(String userId, int state){
        this.userId = userId;
        this.setState(state);
    }
}
