package team.weacsoft.classrepair.bean;

import lombok.*;
import team.weacsoft.classrepair.bean.basic.BasicBeanWithOpenId;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_info")
public class UserInfo extends BasicBeanWithOpenId {

    /**
     * 真实姓名
     */
    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String name;

    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String phone;

    /**
     * 权限
     * 1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @Column(nullable = false, insertable = false, columnDefinition = "int default 1")
    private int role;

    /**
     * 微信头像
     */
    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String avatar;

    /**
     * 微信昵称
     */
    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String nickname;

    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String password;

    /**
     * 学号/工号
     */
    @Column(nullable = false, insertable = false, columnDefinition = "int default 0")
    private Long identityId;

    @Column(nullable = false, insertable = false, columnDefinition = "varchar(50) default ''")
    private String sessionKey;

}
