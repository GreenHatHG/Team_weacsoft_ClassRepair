package team.weacsoft.user.controller;

import cn.hutool.json.JSONObject;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.wx.WxUtils;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.LoginDto;
import team.weacsoft.user.service.UserInfoSelectService;

import java.util.Map;

/**
 * @author GreenHatHG
 * @menu 用户管理
 **/
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserInfoSelectService userInfoService;

    @Autowired
    private WxUtils wxUtils;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 小程序登录接口
     * @param userInfoDto 用户信息
     * @return
     */
    @Log(module = "用户管理", operation = "用户登录")
    @PostMapping("/public/wx/login")
    public ResponseEntity<ApiResp> login(@Validated @RequestBody LoginDto userInfoDto){

        //请求auth.code2Session
        JSONObject code2sessionResp = wxUtils.code2Session(userInfoDto.getCode());

        UserInfoDo userInfo = userInfoService.findByOpenid(code2sessionResp.getStr("openid"));
        //数据库中还没有该人
        if(userInfo == null){
            userInfo = UserInfoDo.builder()
                    .sessionKey(code2sessionResp.getStr("session_key"))
                    .name(userInfoDto.getName())
                    .role(userInfoDto.getRole())
                    .avatar(userInfoDto.getAvatar())
                    .password(userInfoDto.getPassword())
                    .phone(userInfoDto.getPhone())
                    .nickname(userInfoDto.getNickname())
                    .identityId(userInfoDto.getIdentityId()).build();
            userInfo.setOpenid(code2sessionResp.getStr("openid"));
            userInfo = userInfoService.save(userInfo);
        }

        Map<String, String> resp = ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("identityId", String.valueOf(userInfo.getIdentityId()))
                .put("role", String.valueOf(userInfo.getRole()))
                .put("phone", userInfo.getPhone())
                .put("name", userInfo.getName())
                .put("session_key", userInfo.getSessionKey())
                .put("token", jwtUtil.getJWT(userInfo.getId())).build();
        MDC.put("userTableId", userInfo.getId());
        return ApiResp.ok(resp);
    }
}