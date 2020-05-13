package team.weacsoft.user.dto.request;

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
public class WxLoginDto {

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
    @Size(max = 500)
    String avatarUrl = "";

    @Size(max = 100)
    String phone = "";

    /**
     * 微信昵称
     */
    @Size(max = 100)
    String nickName = "";

    @Size(max = 100)
    String password = "";

    /**
     * 学号/工号
     */
    Long identityId = 0L;

    @Min(1) @Max(4)
    Integer role = 1;
}
