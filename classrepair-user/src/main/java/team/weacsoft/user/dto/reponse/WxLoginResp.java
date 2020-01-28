package team.weacsoft.user.dto.reponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author GreenHatHG
 * @since 2020-01-25
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class WxLoginResp extends BaseResp implements Serializable {
    private String token;
    private String sessionKey;
}
