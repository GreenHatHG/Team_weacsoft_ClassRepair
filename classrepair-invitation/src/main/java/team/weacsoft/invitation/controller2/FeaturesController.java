package team.weacsoft.invitation.controller2;

import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.invitation.service.FeaturesService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 邀请码管理
 * @author GreenHatHG
 * @since 2020-01-28
 */
@Validated
@Slf4j
@RestController
@RequestMapping(value="/api/v2/control")
@Api(value = "FeaturesController",  tags = "邀请码模块 | 邀请码管理 LoginController ")
public class FeaturesController {

    /**
     * 邀请码功能开关
     * @param open 1-开启，0-关闭
     */
    @ApiOperation(value="邀请码功能开关", notes="")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PutMapping("/invitation")
    public ResponseEntity<ApiResp> updateInvitation(@RequestParam @Min(0) @Max(1) int open){
        if(open == 0){
            FeaturesService.setOpen(false);
        }else{
            FeaturesService.setOpen(true);
        }
        return ApiResp.ok(
                ImmutableMap.<String, String> builder().put("state", FeaturesService.isOpen()).build());
    }

    /**
     * 获取邀请码功能状态
     */
    @ApiOperation(value="获取邀请码功能状态", notes="")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @GetMapping("/invitation/state")
    public ResponseEntity<ApiResp> getInvitationState(){
        return ApiResp.ok(FeaturesService.isOpen());
    }
}
