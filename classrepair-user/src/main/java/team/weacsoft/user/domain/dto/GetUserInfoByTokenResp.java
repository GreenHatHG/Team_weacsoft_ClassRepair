package team.weacsoft.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
@Builder
public class GetUserInfoByTokenResp {

    private String name;

    private int role;

    private String avatar;

    /**
     * 身份，教师，维护人员等
     */
    private String identity;

    private String phone;
}
