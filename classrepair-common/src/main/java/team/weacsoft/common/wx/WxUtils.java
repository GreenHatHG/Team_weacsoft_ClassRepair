package team.weacsoft.common.wx;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;

/**
 * @author GreenHatHG
 **/

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wx.utils")
public class WxUtils {

    private String appid;

    private String appsecret;

    private String grant_type;

    public JSONObject code2Session(String jsCode){
        JSONObject code2sessionResp =  WxRequests.wxAuth(jsCode, appid, appsecret, grant_type);
        if(code2sessionResp.getInt("errcode") != null){
            throw new BadRequestException(444, JSONUtil.toJsonStr(code2sessionResp));
        }
        return code2sessionResp;
    }

//    public JSONObject code2Session(String jsCode){
//        JSONObject code2sessionResp = new JSONObject();
//        code2sessionResp.put("openid", IdUtil.simpleUUID());
//        code2sessionResp.put("session_key", IdUtil.simpleUUID());
//        return code2sessionResp;
//    }
}
