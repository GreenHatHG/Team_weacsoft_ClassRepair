package team.weacsoft.classrepair.controller;

import cn.hutool.json.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.log.Log;
import team.weacsoft.classrepair.commons.security.JwtUtil;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author GreenHatHG
 * @menu 用户管理
 **/
@RestController
@Validated
@Slf4j
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WxRequests wxRequests;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 用户登录接口
     * @param code 临时登录凭证
     * @param name 真实姓名
     * @param avatar 微信头像
     * @param phone
     * @param nickname 微信昵称
     * @param password
     * @param identityId 学号/工号
     * @param role 权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     * @return
     */
    @Log(module = "用户管理", operation = "用户登录")
    @PostMapping("/wx/login")
    public Result login(@RequestParam @NotBlank @Size(max = 100) String code,
                        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String name,
                        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String avatar,
                        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String phone,
                        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String nickname,
                        @RequestParam(required = false, defaultValue = "") @Size(max = 100) String password,
                        @RequestParam(required = false, defaultValue = "0") long identityId,
                        @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(4) int role,
                        HttpServletResponse response) {

        //请求auth.code2Session
        JSONObject code2sessionResp = wxRequests.code2Session(code);

        UserInfo userInfo = userInfoService.findByOpenid(code2sessionResp.getStr("openid"));
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfo.builder()
                    .sessionKey(code2sessionResp.getStr("session_key"))
                    .name(name)
                    .role(role)
                    .avatar(avatar)
                    .password(password)
                    .phone(phone)
                    .nickname(nickname)
                    .identityId(identityId).build();
            userInfo.setOpenid(code2sessionResp.getStr("openid"));
            userInfoService.save(userInfo);
            // 再次查询，确保插入的数据正确
            userInfo = userInfoService.findByOpenid(code2sessionResp.getStr("openid"));
        }

        Map<String, String> resp = ImmutableMap.<String, String> builder()
                .put("identityId", String.valueOf(userInfo.getIdentityId()))
                .put("role", String.valueOf(userInfo.getRole()))
                .put("phone", userInfo.getPhone())
                .put("name", userInfo.getName())
                .put("session_key", userInfo.getSessionKey()).build();
        MDC.put("userTableId", userInfo.getId());
        response.setHeader("Authorization", jwtUtil.getJWT(userInfo.getId()));
        return ResultFactory.buildSuccessResult(resp);
    }

    /**
     * 接口连接测试
     * @return 已连接上塞伯坦星球
     */
    @Log(module = "连通性测试", operation = "测试是否成功连接")
    @GetMapping("/public/test")
    public Result test(){
        return ResultFactory.buildSuccessResult("已连接上塞伯坦星球");
    }
}