package team.weacsoft.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class UpdateUserInfoDto {

    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String avatarUrl;

    @Size(max = 100)
    private String phone;

    @Size(max = 100)
    private String nickName;

    private Long identityId;

    @Size(max = 100)
    private String password;
}
