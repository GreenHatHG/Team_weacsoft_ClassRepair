package team.weacsoft.common.wx;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 **/

@Component
@Data
@ConfigurationProperties(prefix = "wx.utils")
public class WxUtils {
    private String appid;

    private String secret;

    private String grant_type;

//    public JSONObject code2Session(String jsCode){
//        JSONObject code2sessionResp =  WxRequests.wxAuth(jsCode, APPID, SECRET, GRANT_TYPE);
//        if(code2sessionResp.getInt("errcode") != null){
//            throw new Code2SessionException(JSONUtil.toJsonStr(code2sessionResp));
//        }
//        return code2sessionResp;
//    }

    public JSONObject code2Session(String jsCode){
        JSONObject code2sessionResp = new JSONObject();
        code2sessionResp.put("openid", IdUtil.simpleUUID());
        code2sessionResp.put("session_key", IdUtil.simpleUUID());
        return code2sessionResp;
    }
}
