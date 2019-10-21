package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.entity.Result;
import team.weacsoft.classrepair.entity.ResultFactory;
import team.weacsoft.classrepair.repository.UserInfoRepository;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.util.Jscode2session;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
public class LoginController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private Jscode2session jscode2session;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@NotNull  @RequestBody Map<String, Object> payload) {
        JSONObject jsonObject = JSONUtil.parseObj(payload);

        String jsCode = jsonObject.getStr("code");
        if(jsCode == null){
            return ResultFactory.buildPropertyErroResult("code参数为空");
        }

        //请求auth.code2Session
        JSONObject code2sessionResp = jscode2session.get(jsCode);
        if(code2sessionResp.getInt("errcode") != null){
            operationLogService.addLog(""
                    , EventEnum.Login.event, EventEnum.Login_Failed.event+"->"+"通过wx.login接口获得openid失败");
            return ResultFactory.buildNotAcceptableResult("通过wx.login接口获得openid失败", code2sessionResp);
        }

        Map<String, Object> resp = new HashMap<>(4);
        UserInfo userInfo = null;
        //数据库中还没有该人
        if(userInfoRepository.findByOpenid(code2sessionResp.getStr("openid")) == null){
            userInfo = new UserInfo();

            userInfo.setOpenid(code2sessionResp.getStr("openid"));
            userInfo.setSessionKey(code2sessionResp.getStr("session_key"));
            userInfo.setAvatar(jsonObject.getStr("avatar"));
            userInfo.setPhone(jsonObject.getStr("phone"));
            userInfo.setNickname(jsonObject.getStr("nickname"));
            userInfo.setPassword(jsonObject.getStr("password"));
            userInfo.setIdentityId(jsonObject.getLong("identityId"));

            int role = userInfo.getRole();
            if(role == 0){
                userInfo.setRole(0);
            }
            else if (!(role >= 1 && role <= 4)) {
                return ResultFactory.buildPropertyErroResult("role字段错误，role范围为1到4");
            }
            else{
                userInfo.setRole(role);
            }

            try{
                userInfoRepository.save(userInfo);
            }catch (Exception e){
                e.printStackTrace();
                operationLogService.addLog("", EventEnum.Login.event,
                        EventEnum.Login_Failed.event+"->"+"保存用户信息失败");
                return ResultFactory.buildFORBIDDENResult("保存用户信息失败" + "\n" + e.getMessage());
            }
            resp.put("session_key", code2sessionResp.getStr("session_key"));
        }else{
            userInfo = userInfoRepository.findByOpenid(code2sessionResp.getStr("openid"));
            resp.put("session_key", userInfo.getSessionKey());
        }
        resp.put("identityId", userInfo.getIdentityId());
        resp.put("role", userInfo.getRole());
        resp.put("phone", userInfo.getPhone());
        resp.put("name", userInfo.getName());
        operationLogService.addLog(userInfo.getId()
                , EventEnum.Login.event, EventEnum.Login_SUCCESS.event);
        return ResultFactory.buildSuccessResult(resp);
    }

    @GetMapping("/test")
    public ResponseEntity<Result> test(){
        return ResultFactory.buildSuccessResult("已连接上塞伯坦星球");
    }
}
