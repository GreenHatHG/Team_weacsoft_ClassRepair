package team.weacsoft.user.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;
import team.weacsoft.db.domain.basic.BaseDoWithOpenId;

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
public class UserInfoDo extends BaseDoWithOpenId {

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

    @JSONField(serialize=false)
    @Builder.Default
    @Column(nullable = false)
    private String password = "";

    /**
     * 学号/工号
     */
    @Builder.Default
    @Column(nullable = false)
    private Long identityId = 0L;

    @Builder.Default
    @Column(nullable = false)
    private String sessionKey = "";

}
