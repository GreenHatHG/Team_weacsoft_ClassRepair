package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.OrderItem;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.entity.wx.Code2SessionBody;
import team.weacsoft.classrepair.repository.UserInfoRepository;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.util.WxUtils;

import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
public class LoginController {

    private JSONObject jsonObject = null;
    private UserInfo userInfo = null;

    private UserInfoRepository userInfoRepository;
    private OperationLogService operationLogService;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setOperationLogService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> payload) {
        jsonObject = JSONUtil.parseObj(payload);
        userInfo = new UserInfo();

        userInfo = JSONUtil.toBean(JSONUtil.toJsonStr(payload), UserInfo.class);

        JSONObject code2sessionResp = WxUtils.wxAuth(new Code2SessionBody(jsonObject.getStr("code")));
        userInfo.setOpenid(code2sessionResp.getInt("openid"));
        userInfo.setSessionKey(code2sessionResp.getStr("session_key"));

        int role = userInfo.getRole();
        if (!(role >= 1 && role <= 4)) {
            return ResultFactory.builePropertyErroresult("role字段错误，role范围为1到4");
        }

        operationLogService.addLog(userInfo.getOpenid(), "登录", EventEnum.Login.event);
        try{
            userInfoRepository.save(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult("保存失败，数据库已有该openid或name");
        }

        return ResultFactory.buildSuccessResult("成功");
    }

}
