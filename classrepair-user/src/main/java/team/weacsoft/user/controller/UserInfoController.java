package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.wx.WxUtils;
import team.weacsoft.user.domain.dto.UpdateUserInfoDto;
import team.weacsoft.user.service.UserInfoSelectService;
import team.weacsoft.user.service.UserInfoUpdateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * @author GreenHatHG
 * @menu 用户管理
 */
@RestController
@Validated
@Slf4j
@RequestMapping(value="/user")
public class UserInfoController {

    @Autowired
    private UserInfoSelectService userInfoSelectService;

    @Autowired
    private UserInfoUpdateService userInfoUpdateService;

    @Autowired
    private WxUtils wxUtils;

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @Log(module = "用户管理", operation = "修改用户身份")
    @PutMapping("/actions/update_role")
    public  ResponseEntity<ApiResp> updateRole(@RequestParam @NotBlank @Size(max = 100) String id,
                            @RequestParam @Min(1) @Max(4) int role){
        return ApiResp.ok(userInfoUpdateService.updateRoleById(id, role));
    }


    /**
     * 根据真实姓名搜索用户
     * @param name 真实姓名
     * @return
     */
    @GetMapping("/name")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public  ResponseEntity<ApiResp> findByName(@RequestParam @NotBlank @Size(max = 100) String name){
        return ApiResp.ok(userInfoSelectService.findByName(name));
    }

    /**
     * 根据学号/工号搜索用户
     * @param identity_id 学号/工号
     * @return
     */
    @GetMapping("/identity_id")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> findByIdentityId(@RequestParam Long identity_id){
        return ApiResp.ok(userInfoSelectService.findByIdentityId(identity_id));
    }

    /**
     * 根据微信昵称搜索用户
     * @param nickname 微信昵称，记得转义后再发送，避免特殊字符
     * @return
     */
    @GetMapping("/nickname")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> findByNickname(@RequestParam @NotBlank @Size(max = 100) String nickname){
        return ApiResp.ok(userInfoSelectService.findByNickname(nickname));
    }

    /**
     * 修改用户信息
     * @param dto 要修改的信息，不需要修改的字段留空
     * @return
     */
    @Log(module = "用户管理", operation = "修改用户信息")
    @PutMapping("/actions/update_info")
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    public ResponseEntity<ApiResp> updateUserInfo(HttpServletRequest request,
            @Validated @RequestBody UpdateUserInfoDto dto){
        return ApiResp.ok(userInfoUpdateService.updateUserInfo(dto, request));
    }

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping("/userlist")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> getUserList(Pageable pageable){
        return ApiResp.ok(userInfoSelectService.getUserList(pageable));
    }


}
