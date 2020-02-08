package team.weacsoft.invitation.dto.request;

import lombok.Data;

/**
 * @author GreenHatHG
 * @since 2020-02-08
 */

@Data
public class UpdateRoleByCodeDto {
    private String code;
    private String password;
}
