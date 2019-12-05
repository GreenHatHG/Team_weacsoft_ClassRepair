package team.weacsoft.classrepair.bean;

import lombok.*;
import team.weacsoft.classrepair.bean.basic.BaseBeanWithOpenId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户表
 * @author GreenHatHG
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class UserInfo extends BaseBeanWithOpenId {

    /**
     * 真实姓名
     */
    @Builder.Default
    @Column(nullable = false)
    private String name = "";

    @Builder.Default
    @Column(nullable = false)
    private String phone = "";

    /**
     * 权限
     * 1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @Builder.Default
    @Column(nullable = false)
    private int role = 1;

    /**
     * 微信头像
     */
    @Builder.Default
    @Column(nullable = false)
    private String avatar = "";

    /**
     * 微信昵称
     */
    @Builder.Default
    @Column(nullable = false)
    private String nickname = "";

    @Builder.Default
    @Column(nullable = false)
    private String password = "";

    /**
     * 学号/工号
     */
    @Builder.Default
    @Column(nullable = false)
    private long identityId = (long)0;

    @Builder.Default
    @Column(nullable = false)
    private String sessionKey = "";

}
