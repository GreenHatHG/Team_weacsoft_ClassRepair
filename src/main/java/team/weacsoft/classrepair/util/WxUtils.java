package team.weacsoft.classrepair.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import team.weacsoft.classrepair.contests.WxAPIEnum;
import team.weacsoft.classrepair.entity.wx.Code2SessionBody;

/**
 * 微信小程序请求工具类
 * @author GreenHatHG
 **/
public class WxUtils {

    public static JSONObject wxAuth(Code2SessionBody code2SessionBody){
        String result = HttpRequest.post(WxAPIEnum.CODE2SEESION.api).body(JSONUtil.parseObj(code2SessionBody)).execute().body();
        return JSONUtil.parseObj(result);
    }
}
