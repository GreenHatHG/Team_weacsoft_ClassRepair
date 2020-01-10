package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.user.domain.dto.WebLoginDto;
import team.weacsoft.user.domain.dto.WxLoginDto;
import team.weacsoft.user.service.LoginService;

/**
 * @author GreenHatHG
 * @menu 用户管理
 **/
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 小程序登录接口
     * @param userInfoDto 用户信息
     * @return
     */
    @Log(module = "用户管理", operation = "用户使用微信登录")
    @PostMapping("/public/wx/login")
    public ResponseEntity<ApiResp> wxLogin(@Validated @RequestBody WxLoginDto userInfoDto){
        return ApiResp.ok(loginService.wxLogin(userInfoDto));
    }

    /**
     * 网页登录接口
     * @param userInfoDto
     * @return
     */
    @Log(module = "用户管理", operation = "用户在网页登录")
    @PostMapping("/public/web/login")
    public ResponseEntity<ApiResp> webLogin(@Validated @RequestBody WebLoginDto userInfoDto){
        return ApiResp.ok(loginService.webLogin(userInfoDto));
    }


}