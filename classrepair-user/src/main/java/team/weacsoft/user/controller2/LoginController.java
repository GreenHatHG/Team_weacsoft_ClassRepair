package team.weacsoft.user.controller2;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.user.dto.request.WebLoginDto;
import team.weacsoft.user.dto.request.WxLoginDto;
import team.weacsoft.user.service.ILoginService;

/**
 * @author GreenHatHG
 * @menu 用户管理
 **/
@RestController
@Slf4j
@RequestMapping("/api/v2")
public class LoginController {

    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 小程序登录接口
     * @param userInfoDto 用户信息
     * @return
     */
    @Log(module = "用户管理", operation = "用户使用微信登录")
    @PostMapping("/public/wx/login")
    public ResponseEntity<ApiResp> wxLogin(@Validated @RequestBody WxLoginDto userInfoDto) throws WxErrorException {
        return ApiResp.ok(loginService.wxLogin(userInfoDto));
    }

    /**
     * 网页登录接口
     */
    @Log(module = "用户管理", operation = "用户在网页登录")
    @PostMapping("/public/web/login")
    public ResponseEntity<ApiResp> webLogin(@Validated @RequestBody WebLoginDto webLoginDto){
        return ApiResp.ok(loginService.webLogin(webLoginDto));
    }

}