package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BaseBean;

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
public class TimeTable extends BaseBean {

    @Column(nullable = false, unique = true)
    private String userId;

    public TimeTable(String userId, int status){
        this.userId = userId;
        this.setState(status);
    }
}
