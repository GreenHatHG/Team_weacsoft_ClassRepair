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
public class userinfo extends AbstractBasicBean {

    @Column(nullable = false, unique = true)
    private int openid;

    private String username;

    private String phone;

    @Column(nullable = false)
    private String role;

    private String avatar;

    private String birth;

    private String nickname;

    private String password;

    private String phoneType;

    private String schoolId;

    private String signature;

    private String wechat;

    private String sessionKey;

    private String token;
}
