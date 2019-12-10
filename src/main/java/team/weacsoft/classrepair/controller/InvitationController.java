package team.weacsoft.classrepair.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.service.InvitationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 用户管理
 */

@RestController
@Validated
@Slf4j
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    /**
     * 获得邀请码
     * @param request
     * @return
     */
    @GetMapping("/user/invitation_code")
    public Result getCode(HttpServletRequest request){
        return ResultFactory.buildSuccessResult(
                invitationService.getInvitionCode(request.getHeader("Authorization")));
    }

    /**
     * 根据邀请码提权
     * @param request
     * @param code
     * @return
     */
    @PutMapping("/user/actions/update_role")
    public Result updateRoleByCode(HttpServletRequest request,
                                   @RequestParam @NotBlank @Size(max = 100) String code){
        return ResultFactory.buildSuccessResult(
                invitationService.updtaeRoleByCode(code, request.getHeader("Authorization")));
    }
}
