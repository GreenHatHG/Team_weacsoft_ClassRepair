package team.weacsoft.classrepair.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Table(name = "user_info")
public class UserInfo extends AbstractBasicBean {

    @Column(nullable = false, unique = true)
    private int openid;

    /**
     * 真实姓名
     */
    private String name;

    private String phone;

    /**
     * 权限
     * 1-普通人员，2-维护人员，3-老师，4-超级管理员
     */
    @Column(nullable = false)
    private int role;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 生日
     */
    private String birth;

    /**
     * 微信昵称
     */
    private String nickname;

    private String password;

    /**
     * 手机型号
     */
    private String phoneType;

    /**
     * 学号/工号
     */
    private String schoolId;

    /**
     * 微信签名
     */
    private String signature;

    /**
     * 微信号
     */
    private String wechat;

    private String sessionKey;

    private String token;
}
