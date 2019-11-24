package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.contests.EventEnum;
import team.weacsoft.classrepair.service.OperationLogService;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author GreenHatHG
 **/

@RestController
@RequestMapping(value="${api}")
@Validated
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private WxRequests wxRequests;

    @PostMapping("/login")
    public Result login(@RequestParam @NotBlank @Size(max = 100) String code,
                        @RequestParam(required = false) @Size(max = 100) String avatar,
                        @RequestParam(required = false) @Size(max = 100) String phone,
                        @RequestParam(required = false) @Size(max = 100) String nickname,
                        @RequestParam(required = false) @Size(max = 100) String password,
                        @RequestParam(required = false) Long identityId,
                        @RequestParam(required = false, defaultValue = "0") @Min(0) @Max(4) int role
                        ) {

        //请求auth.code2Session
        JSONObject code2sessionResp = wxRequests.code2Session(code);

        UserInfo userInfo = userInfoService.findByOpenid(code2sessionResp.getStr("openid"));
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfo.builder()
                    .sessionKey(code2sessionResp.getStr("session_key"))
                    .avatar(avatar)
                    .password(password)
                    .phone(phone)
                    .nickname(nickname)
                    .identityId(identityId).build();
            userInfo.setOpenid(code2sessionResp.getStr("openid"));
            userInfoService.save(userInfo);
        }

        Map<String, String> resp = ImmutableMap.<String, String> builder()
                .put("identityId", String.valueOf(userInfo.getIdentityId()))
                .put("role", String.valueOf(userInfo.getRole()))
                .put("phone", userInfo.getPhone())
                .put("name", userInfo.getName())
                .put("session_key", userInfo.getSessionKey()).build();

        operationLogService.addLog(userInfo.getId()
                , EventEnum.Login.event, EventEnum.Login_SUCCESS.event);

        return ResultFactory.buildSuccessResult(resp);
    }

    @GetMapping("/test")
    public Result test(){
        return ResultFactory.buildSuccessResult("已连接上塞伯坦星球");
    }
}
