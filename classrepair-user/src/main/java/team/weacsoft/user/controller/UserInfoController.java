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
import team.weacsoft.user.domain.dto.FieldDtoEnum;
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

    private UserInfoSelectService userInfoSelectService;
    private UserInfoUpdateService userInfoUpdateService;

    @Autowired
    public UserInfoController(UserInfoSelectService userInfoSelectService, UserInfoUpdateService userInfoUpdateService) {
        this.userInfoSelectService = userInfoSelectService;
        this.userInfoUpdateService = userInfoUpdateService;
    }

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @Log(module = "用户管理", operation = "修改用户身份")
    @PutMapping("/actions/update_role")
    public  ResponseEntity<ApiResp> updateRole(@RequestParam @NotBlank @Size(max = 100) String id,
                            @RequestParam @Min(1) @Max(4) int role){
        return ApiResp.ok(userInfoUpdateService.updateRoleById(id, role));
    }

    /**
     * 根据特定字段可选分页搜索用户
     * @param field 字段
     * @param value 字段值
     * @param pageable 分页
     */
    @GetMapping("/field")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> findByCriteria(FieldDtoEnum field,
                                                  @RequestParam @NotBlank @Size(max = 100) String value,
                                                  Pageable pageable, HttpServletRequest request){
        return ApiResp.ok(userInfoSelectService.findByCriteria(field.toString(), value, pageable, request));
    }

    /**
     * 修改用户信息
     * @param dto 要修改的信息，不需要修改的字段留空
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
     */
    @GetMapping("/userlist")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> getUserList(Pageable pageable){
        return ApiResp.ok(userInfoSelectService.getUserList(pageable));
    }

}
