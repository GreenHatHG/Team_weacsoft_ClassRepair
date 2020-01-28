package team.weacsoft.user.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
@ToString
public class GetUserInfoByTokenResp extends BaseResp implements Serializable {
    /**
     * 身份：教师，维护人员等
     */
    private String identity;
}
