package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.service.UserInfoTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    public ResponseEntity<ApiResp> getUserInfoByToken(HttpServletRequest request,
            @RequestParam @NotBlank @Size(max = 200) String token){
        String requestToken = jwtUtil.getJwtFromRequest(request);
        if(!StringUtils.equals(requestToken, token)){
            throw new UnauthorizedException("身份信息不正确");
        }
        return ApiResp.ok(userInfoTokenService.getUserInfoByTokenResp(token));
    }
}
