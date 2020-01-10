package team.weacsoft.invitation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.invitation.service.InvitationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 邀请码管理
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/invitation_code")
public class InvitationController {

    @Autowired
    private InvitationService invitationService;

    /**
     * 获得邀请码
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "邀请码管理", operation = "获得邀请码")
    @GetMapping("")
    public ResponseEntity<ApiResp> getCode(){
        return ApiResp.ok(invitationService.getInvitionCode());
    }

    /**
     * 根据邀请码提权
     * @param request
     * @param code
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @PostMapping("/actions/update_role")
    public ResponseEntity<ApiResp> updateRoleByCode(HttpServletRequest request,
                                   @RequestParam @NotBlank @Size(max = 100) String code){
        return ApiResp.ok(invitationService
                .updtaeRoleByCode(code, request.getHeader("Authorization")));
    }
}
