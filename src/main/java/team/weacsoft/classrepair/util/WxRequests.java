package team.weacsoft.classrepair.util;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 **/

@Component
@Data
public class WxRequests {
    @Value("${APPID}")
    private String APPID;

    @Value("${APPSECRET}")
    private String SECRET;

    @Value("${GRANT_TYPE}")
    private String GRANT_TYPE;

    @Value("${template_id}")
    private String TEMPLATE_ID;

    public JSONObject code2Session(String jsCode){
        return WxUtils.wxAuth(jsCode, APPID, SECRET, GRANT_TYPE);
    }

    public JSONObject subscribeMessage(String touser, String page){
        return WxUtils.subscribeMessage(touser, TEMPLATE_ID, page, "");
    }
}
