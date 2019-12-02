package team.weacsoft.classrepair.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 操作日志
 * @author GreenHatHG
 **/

@Data
@Entity
@Table(name = "operation_log")
public class OperationLog {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 此id为userInfo表对应的id字段
     */
    @Column(nullable = false)
    private String userInfoId;

    /**
     * 事件
     */
    @Column(nullable = false)
    private String event;

    /**
     * 内容
     */
    @Lob
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    public OperationLog(String userInfoId, String event, String content) {
        this.userInfoId = userInfoId;
        this.event = event;
        this.content = content;
    }
}
