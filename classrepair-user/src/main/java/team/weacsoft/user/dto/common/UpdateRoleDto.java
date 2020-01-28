package team.weacsoft.user.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Getter
@Setter
@ToString
public class UpdateRoleDto {
    private Integer id;
    private Integer role;
}
