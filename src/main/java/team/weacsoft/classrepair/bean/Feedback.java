package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 用户反馈表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "feedback")
public class Feedback extends AbstractBasicBean {

    /**
     * 用户id
     */
    @Column(nullable = false, unique = true)
    private int userId;

    /**
     * 手机号
     */
    @Column(nullable = false)
    private String phone;

    /**
     * 反馈内容
     */
    @Lob
    @Column(nullable = false)
    private String content;
}
