package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.user.dto.request.FieldDtoEnum;
import team.weacsoft.user.dto.request.GetPhoneDto;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;


/**
 * @author GreenHatHG
 * @menu 用户管理
 */
@RestController
@Validated
@Slf4j
@RequestMapping(value="/user")
public class UserInfoController {
    private IUserInfoService userInfoService;

    @Autowired
    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/token/info")
    public ResponseEntity<ApiResp> getUserInfoByToken(HttpServletRequest request){
        return ApiResp.ok(userInfoService.getUserInfoByToken(request));
    }

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @Log(module = "用户管理", operation = "修改用户身份")
    @PutMapping("/actions/update_role")
    public  ResponseEntity<ApiResp> updateRoleById(@RequestParam @NotBlank @Size(max = 100) String id,
                            @RequestParam @NotNull @Min(1) @Max(4) Integer role){
        return ApiResp.ok(userInfoService.updateRoleById(id, role));
    }

    /**
     * 根据特定字段可选分页搜索用户
     * @param field 字段
     * @param value 字段值
     * @param page 分页
     */
    @GetMapping("/field")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> findByCriteria(FieldDtoEnum field,
                                                  @RequestParam @NotBlank @Size(max = 100) String value,
                                                  PageRequest page, HttpServletRequest request){
        return ApiResp.ok(userInfoService.getUserInfoByField(field.toString(), value, page, request));
    }

    /**
     * 修改用户自己的信息
     * @param dto 要修改的信息，不需要修改的字段留空
     */
    @Log(module = "用户管理", operation = "修改自己的用户信息")
    @PutMapping("/actions/update_info")
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    public ResponseEntity<ApiResp> updateMyUserInfo(HttpServletRequest request,
            @Validated @RequestBody UpdateUserInfoDto dto){
        return ApiResp.ok(userInfoService.updateMyUserInfo(request, dto));
    }

    /**
     * 根据id修改用户信息
     */
    @Log(module = "用户管理", operation = "根据id修改用户信息")
    @PutMapping("/actions/update_info_id")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> updateOtherUserInfo(@NotNull Integer id, @Validated @RequestBody UpdateUserInfoDto dto){
        return ApiResp.ok(userInfoService.updateOtherUserInfo(id, dto));
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/userlist")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    public ResponseEntity<ApiResp> getUserList(PageRequest page){
        return ApiResp.ok(userInfoService.getUserList(page));
    }

    /**
     * 解析用户手机号码
     */
    @PostMapping("/actions/get_phone")
    public ResponseEntity<ApiResp> getPhone(@Validated @RequestBody GetPhoneDto dto, HttpServletRequest request) throws WxErrorException {
        userInfoService.getPhone(dto, request);
        return ApiResp.ok("解析成功");
    }
}
