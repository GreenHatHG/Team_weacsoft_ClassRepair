package team.weacsoft.user.controller2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.user.dto.request.FieldDtoEnum;
import team.weacsoft.user.dto.request.UpdateUserInfoDto;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;


/**
 * @author GreenHatHG
 * @menu 用户管理
 */
@Api(value = "user",  tags = "用户模块 | 用户信息 UserInfoController ")
@RestController
@Validated
@Slf4j
@RequestMapping(value="/api/v2/user")
public class UserInfoController {
    private IUserInfoService userInfoService;

    @Autowired
    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     *
     * @param request
     * @return
     */
    @ApiOperation(value="获取用户token", notes="")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @GetMapping("/token/info")
    public ResponseEntity<ApiResp> getUserInfoByToken(HttpServletRequest request){
        return ApiResp.ok(userInfoService.getUserInfoByToken(request));
    }

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限
     */
    @ApiOperation(value="修改用户身份", notes="")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
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
    @ApiOperation(value="根据特定字段可选分页搜索用户", notes="")
    @GetMapping("/field")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    public ResponseEntity<ApiResp> findByCriteria(FieldDtoEnum field,
                                                  @RequestParam @NotBlank @Size(max = 100) String value,
                                                  PageRequest page, HttpServletRequest request){
        return ApiResp.ok(userInfoService.getUserInfoByField(field.toString(), value, page, request));
    }

    /**
     * 修改用户自己的信息
     * @param dto 要修改的信息，不需要修改的字段留空
     */
    @ApiOperation(value="修改用户自己的信息", notes="")
    @Log(module = "用户管理", operation = "修改自己的用户信息")
    @PutMapping("/actions/update_info")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    public ResponseEntity<ApiResp> updateMyUserInfo(HttpServletRequest request,
            @Validated @RequestBody UpdateUserInfoDto dto){
        return ApiResp.ok(userInfoService.updateMyUserInfo(request, dto));
    }

    /**
     * 根据id修改用户信息
     */
    @ApiOperation(value="根据id修改用户信息", notes="")
    @Log(module = "用户管理", operation = "根据id修改用户信息")
    @PutMapping("/actions/update_info_id")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    public ResponseEntity<ApiResp> updateOtherUserInfo(@NotNull Integer id, @Validated @RequestBody UpdateUserInfoDto dto){
        return ApiResp.ok(userInfoService.updateOtherUserInfo(id, dto));
    }

    /**
     * 获取用户列表
     */
    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping("/userlist")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    public ResponseEntity<ApiResp> getUserList(PageRequest page){
        return ApiResp.ok(userInfoService.getUserList(page));
    }

}
