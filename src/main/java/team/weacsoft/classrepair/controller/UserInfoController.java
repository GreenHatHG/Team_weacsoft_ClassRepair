package team.weacsoft.classrepair.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author GreenHatHG
 */

@RestController
@Validated
@Slf4j
@RequestMapping(value="/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private WxRequests wxRequests;

    @PutMapping("/role")
    public Result udateRole(@RequestParam @NotBlank @Size(max = 100) String code,
                            @RequestParam @Min(1) @Max(4) int role){
        UserInfo userInfo = userInfoService.findByOpenIdAndCheck(
                wxRequests.code2Session(code).getStr("openid"), code);
        userInfo.setRole(role);
        userInfoService.save(userInfo);
        userInfo = userInfoService.findByOpenid(userInfo.getOpenid());
        Map<String, String> resp =  ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("role", String.valueOf(userInfo.getRole())).build();
        return ResultFactory.buildSuccessResult(resp);
    }
}
