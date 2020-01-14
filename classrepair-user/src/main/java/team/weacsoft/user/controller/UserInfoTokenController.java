package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.service.UserInfoTokenService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GreenHatHG
 */

@RestController
@Validated
@Slf4j
@RequestMapping(value="/user")
public class UserInfoTokenController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserInfoTokenService userInfoTokenService;

    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/token/info")
    public ResponseEntity<ApiResp> getUserInfoByToken(HttpServletRequest request){
        return ApiResp.ok(userInfoTokenService.getUserInfoByTokenResp(request));
    }
}
