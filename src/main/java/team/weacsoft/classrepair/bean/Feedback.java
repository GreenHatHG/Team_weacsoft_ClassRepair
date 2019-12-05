package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BaseBeanWithOpenId;

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
public class Feedback extends BaseBeanWithOpenId {

    /**
     * 反馈人的手机号
     */
    @Column(nullable = false)
    private String phone = "";

    /**
     * 反馈内容,文本
     */
    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * 反馈图片存放图片路径
     */
    @Column(nullable = false)
    private String imgUrl = "";
}
