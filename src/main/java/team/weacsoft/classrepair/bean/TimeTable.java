package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class TimeTable extends AbstractBasicBean {

    @Column(nullable = false, unique = true)
    private int userId;
}
