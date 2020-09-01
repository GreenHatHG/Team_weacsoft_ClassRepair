package team.weacsoft.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import team.weacsoft.common.persistence.BaseWxEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
public class UserInfo extends BaseWxEntity {

    /**
     * 微信头像
     */
    @Builder.Default
    private String avatarUrl = "";

    /**
     * 学号/工号
     */
    @Builder.Default
    private Long identityId = 0L;

    /**
     * 真实姓名
     */
    @Builder.Default
    private String name = "";

    /**
     * 微信昵称
     */
    @Builder.Default
    private String nickName = "";

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(serialize=false)
    @Builder.Default
    private String password = "";

    /**
     * 设置成varchar防止一些特殊电话
     */
    @Builder.Default
    private String phone = "";

    /**
     * 1-普通人员, 4-维护人员, 5-课室团队负责人, 6-课室管理员 , 7-老师, 9-超级管理员
     */
    @Builder.Default
    private Integer role = 1;

    /**
     * 微信小程序登录后返回的字段
     */
    @Builder.Default
    private String sessionKey = "";

    /**
     * 判断是否是一名合法维护人员，合法true
     * @return
     */
    public boolean isPerfect(){
        if (getPhone()==null&&getPhone()==null&&getIdentityId()==null)
            return false;
        return true;
    }

}
