package team.weacsoft.classrepair.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBean;
import team.weacsoft.classrepair.bean.basic.BasicBeanWithOpenId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 操作日志
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "operation_log")
public class OperationLog extends BasicBean {

    /**
     * 此id为userInfo表对应的id字段
     */
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

    public OperationLog(String userInfoId, String event, String content) {
        this.userInfoId = userInfoId;
        this.event = event;
        this.content = content;
    }
}
