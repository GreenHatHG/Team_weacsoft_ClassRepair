package team.weacsoft.invitation.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.invitation.service.FeaturesService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author GreenHatHG
 * @menu 邀请码管理
 */

@Validated
@Slf4j
@RestController
@RequestMapping(value="/control")
public class FeaturesController {

    /**
     * 邀请码功能开关
     * @param open 1-开启，0-关闭
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
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
}
