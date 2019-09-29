package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

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
public class TimeTable extends AbstractBasicBean {

    @Column(nullable = false, unique = true)
    private int userId;

    public TimeTable(int userId, int status){
        this.userId = userId;
        this.setStatus(status);
    }
}
