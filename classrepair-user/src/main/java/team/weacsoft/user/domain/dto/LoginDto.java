package team.weacsoft.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class LoginDto {

    /**
     * 临时登录凭证
     */
    @NotBlank
    @Size(max = 100)
    String code;

    /**
     * 真实姓名
     */
    @Size(max = 100)
    String name = "";

    /**
     * 微信头像
     */
    @Size(max = 100)
    String avatar = "";

    @Size(max = 100)
    String phone = "";

    /**
     * 微信昵称
     */
    @Size(max = 100)
    String nickname = "";

    @Size(max = 100)
    String password = "";

    /**
     * 学号/工号
     */
    Long identityId = (long)0;

    /**
     * 权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @Min(1) @Max(4)
    Integer role = 1;
}
