package team.weacsoft.classrepair.commons.util;

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

//    public JSONObject code2Session(String jsCode){
//        JSONObject code2sessionResp =  WxUtils.wxAuth(jsCode, APPID, SECRET, GRANT_TYPE);
//        if(code2sessionResp.getInt("errcode") != null){
//            throw new Code2SessionException(JSONUtil.toJsonStr(code2sessionResp));
//        }
//        return code2sessionResp;
//    }

    public JSONObject code2Session(String jsCode){
        JSONObject code2sessionResp = new JSONObject();
        code2sessionResp.put("openid", "a5CywnEAyIEFcBSaF4Bp6A");
        code2sessionResp.put("session_key", "a5CywnEAyIEFcBSaF4Bp6A==");
        return code2sessionResp;
    }
}
