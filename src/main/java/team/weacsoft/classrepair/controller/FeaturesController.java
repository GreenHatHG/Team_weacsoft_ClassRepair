package team.weacsoft.classrepair.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.control.InvitationControl;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author GreenHatHG
 * @menu 系统控制
 */

@RestController(value = "/control")
@Validated
@Slf4j
public class FeaturesController {

    /**
     * 邀请码功能开关
     * @param open 1-开启，0-关闭
     * @return
     */
    @PutMapping("/invitation")
    public Result updateInvitation(@RequestParam @Min(0) @Max(1) int open){
        if(open == 0){
            InvitationControl.setOpen(false);
        }else{
            InvitationControl.setOpen(true);
        }
        return ResultFactory.buildSuccessResult(
                ImmutableMap.<String, String> builder().put("state", InvitationControl.isOpen()).build());
    }
}
