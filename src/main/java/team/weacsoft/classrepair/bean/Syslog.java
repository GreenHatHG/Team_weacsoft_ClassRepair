package team.weacsoft.classrepair.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author GreenHatHG
 */
@Data
@Entity(name = "sys_log")
public class Syslog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable=false)
    private String userid;

    @Column(nullable=false)
    private String operateModule;

    private String operateType;

    private Date operateTime;

    private String operateContent;

    private String operateResult;
}
