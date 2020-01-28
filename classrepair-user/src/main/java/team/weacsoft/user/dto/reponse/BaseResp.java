package team.weacsoft.user.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@Getter
@Setter
@ToString
public class BaseResp implements Serializable {
    private Integer id;
    private String name;
    private Integer role;
    private String avatarUrl;
    private String phone;
    private Long identityId;
    private String nickName;
}
