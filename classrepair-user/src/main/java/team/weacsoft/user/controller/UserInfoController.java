package team.weacsoft.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.wx.WxUtils;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.UpdateUserInfoDto;
import team.weacsoft.user.service.UserInfoService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.URLDecoder;

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
    private UserInfoService userInfoService;

    @Autowired
    private WxUtils wxUtils;

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     * @return
     */
    @Log(module = "用户管理", operation = "修改用户身份")
    @PutMapping("/actions/update_role")
    public  ResponseEntity<ApiResp> updateRole(@RequestParam @NotBlank @Size(max = 100) String id,
                            @RequestParam @Min(1) @Max(4) int role){
        return ApiResp.ok(userInfoService.updateRoleById(id, role));
    }


    /**
     * 根据真实姓名搜索用户
     * @param name 真实姓名
     * @return
     */
    @GetMapping("/name")
    public  ResponseEntity<ApiResp> findByName(@RequestParam @NotBlank @Size(max = 100) String name){
        return ApiResp.ok(userInfoService.findByName(name));
    }

    /**
     * 根据学号/工号搜索用户
     * @param identity_id 学号/工号
     * @return
     */
    @GetMapping("/identity_id")
    public ResponseEntity<ApiResp> findByIdentityId(@RequestParam Long identity_id){
        return ApiResp.ok(userInfoService.findByIdentityId(identity_id));
    }

    /**
     * 根据微信昵称搜索用户
     * @param nickname 微信昵称，记得转义后再发送，避免特殊字符
     * @return
     */
    @GetMapping("/nickname")
    public ResponseEntity<ApiResp> findByNickname(@RequestParam @NotBlank @Size(max = 100) String nickname){
        try{
            nickname = URLDecoder.decode(nickname, "utf-8");
        }catch (Exception e){
            throw new BadRequestException(432, "转义失败：nickname:"+nickname);
        }
        return ApiResp.ok(userInfoService.findByNickname(nickname));
    }

    //todo 管理员或者以上可以修改别人的信息普通用户只能修改自己的信息，需要设置安全验证

    /**
     * 修改用户信息
     * @param dto 要修改的信息，不需要修改的字段留空
     * @return
     */
    @Log(module = "用户管理", operation = "修改用户信息")
    @PutMapping("/actions/update_info")
    public ResponseEntity<ApiResp> updateUserInfo(@Validated @RequestBody UpdateUserInfoDto dto){
        UserInfoDo userInfo = userInfoService.findById(dto.getId());
        if(!"".equals(dto.getName())){
            userInfo.setName(dto.getName());
        }
        if(!"".equals(dto.getAvatar())){
            userInfo.setName(dto.getAvatar());
        }
        if(!"".equals(dto.getPhone())){
            userInfo.setName(dto.getPhone());
        }
        if(!"".equals(dto.getNickname())){
            userInfo.setName(dto.getNickname());
        }
        if(dto.getIdentityId() != 0){
            userInfo.setIdentityId(dto.getIdentityId());
        }

        return ApiResp.ok(userInfoService.save(userInfo));
    }

}
