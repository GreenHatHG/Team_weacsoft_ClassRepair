package team.weacsoft.classrepair.bean;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import team.weacsoft.classrepair.bean.basic.BasicBeanWithOpenId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//todo 注释
/**
 * 用户表
 * @author GreenHatHG
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@Table(name = "user_info")
public class UserInfo extends BasicBeanWithOpenId {

    /**
     * 真实姓名
     */
    private String name;

    private String phone;

    /**
     * 权限
     * 1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @Column(nullable = false)
    private int role;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 微信昵称
     */
    private String nickname;

    private String password;

    /**
     * 学号/工号
     */
    private Long identityId;

    private String sessionKey;
}
