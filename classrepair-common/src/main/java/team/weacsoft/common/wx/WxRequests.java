package team.weacsoft.common.wx;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序请求工具类
 * @author GreenHatHG
 **/

public class WxRequests {

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
        String result = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(payload).execute().body();
        return JSONUtil.parseObj(result);
    }

    /**
     * 调用接口下发订阅消息
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     * @param touser
     * @param template_id
     * @param page
     * @param data
     * @return
     */
//    public static JSONObject subscribeMessage(String touser, String template_id, String page, String data){
//            Map<String, Object> payload = new HashMap<>(4);
//            payload.put("touser", touser);
//            payload.put("template_id", template_id);
//            payload.put("page", page);
//            payload.put("data", data);
//            String result = HttpRequest.post(WxAPIEnum.SUBSCRIBE_MESSAGE.api).form(payload).execute().body();
//            return JSONUtil.parseObj(result);
//    }


}
