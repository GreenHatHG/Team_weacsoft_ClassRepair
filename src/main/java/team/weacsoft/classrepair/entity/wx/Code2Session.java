package team.weacsoft.classrepair.entity.wx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author GreenHatHG
 **/

@Data
public class Code2Session {

    @Value("${APPID}")
    private String appid;

    @Value("${APPSECRET}")
    private String secret;

    private String jsCode;

    private String grantType = "authorization_code";

}
