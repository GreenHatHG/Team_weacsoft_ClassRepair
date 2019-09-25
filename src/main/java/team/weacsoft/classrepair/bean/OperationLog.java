package team.weacsoft.classrepair.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@AllArgsConstructor
@Entity
@Table(name = "operation_log")
public class OperationLog extends AbstractBasicBean{

    @Column(nullable = false, unique = true)
    private int userId;

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
}
