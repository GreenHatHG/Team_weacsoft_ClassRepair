package team.weacsoft.classrepair.commons.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.commons.exception.Code2SessionException;
import team.weacsoft.classrepair.service.OperationLogService;

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

    @Autowired
    private OperationLogService operationLogService;

    public JSONObject code2Session(String jsCode){
        JSONObject code2sessionResp =  WxUtils.wxAuth(jsCode, APPID, SECRET, GRANT_TYPE);
        if(code2sessionResp.getInt("errcode") != null){
//            operationLogService.addLog(jsCode
//                    , EventEnum.ORDERITEM.event, EventEnum.ORDERITEM_FAILED.event+"-->通过wx.login接口获得openid失败");
            throw new Code2SessionException(JSONUtil.toJsonStr(code2sessionResp));
        }
        return code2sessionResp;
    }

    public JSONObject subscribeMessage(String touser, String page){
        return WxUtils.subscribeMessage(touser, TEMPLATE_ID, page, "");
    }
}
