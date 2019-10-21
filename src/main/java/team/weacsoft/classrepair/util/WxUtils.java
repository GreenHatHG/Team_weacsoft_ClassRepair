package team.weacsoft.classrepair.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.contests.WxAPIEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序请求工具类
 * @author GreenHatHG
 **/

public class WxUtils {

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     * @param jsCode
     * @param appid
     * @param secret
     * @param grantType
     * @return
     */
    public static JSONObject wxAuth(String jsCode, String appid, String secret, String grantType){
        Map<String, Object> payload = new HashMap<>();
        payload.put("appid", appid);
        payload.put("secret", secret);
        payload.put("js_code", jsCode);
        payload.put("grant_type", grantType);
        String result = HttpRequest.get(WxAPIEnum.CODE2SEESION.api).form(payload).execute().body();
        return JSONUtil.parseObj(result);
    }
}
