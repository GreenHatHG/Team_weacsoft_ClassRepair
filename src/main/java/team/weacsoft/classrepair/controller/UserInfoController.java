package team.weacsoft.classrepair.controller;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.commons.exception.CustomException;
import team.weacsoft.classrepair.commons.log.Log;
import team.weacsoft.classrepair.commons.util.WxRequests;
import team.weacsoft.classrepair.service.UserInfoService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private WxRequests wxRequests;

    /**
     * 修改用户身份
     * @param id 用户表id
     * @param role 修改后的权限，1-普通人员，2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
     * @return
     */
    @Log(module = "用户管理", operation = "修改用户身份")
    @PutMapping("/role")
    public Result udateRole(@RequestParam @NotBlank @Size(max = 100) String id,
                            @RequestParam @Min(1) @Max(4) int role){
        UserInfo userInfo = userInfoService.findById(id);
        userInfo.setRole(role);
        userInfoService.save(userInfo);
        userInfo = userInfoService.findById(userInfo.getId());
        Map<String, String> resp =  ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("role", String.valueOf(userInfo.getRole())).build();
        return ResultFactory.buildSuccessResult(resp);
    }

    /**
     * 根据真实姓名搜索用户
     * @param name 真实姓名
     * @return
     */
    @GetMapping("/name")
    public Result findByName(@RequestParam @NotBlank @Size(max = 100) String name){
        List<UserInfo> userInfos = userInfoService.findByName(name);
        List<Map<String, String>> resp = new ArrayList<>();
        for(UserInfo userInfo : userInfos){
            resp.add(filterUserInfo(userInfo));
        }
        return ResultFactory.buildSuccessResult(resp);
    }

    /**
     * 根据学号/工号搜索用户
     * @param identity_id 学号/工号
     * @return
     */
    @GetMapping("/identity_id")
    public Result findByIdentityId(@RequestParam long identity_id){
        return ResultFactory.buildSuccessResult(filterUserInfo(
                userInfoService.findByIdentityId(identity_id)));
    }

    /**
     * 根据微信昵称搜索用户
     * @param nickname 微信昵称，记得转义后再发送，避免特殊字符
     * @return
     */
    @GetMapping("/nickname")
    public Result findByNickname(@RequestParam @NotBlank @Size(max = 100) String nickname){
        try{
            nickname = URLDecoder.decode(nickname, "utf-8");
        }catch (Exception e){
            throw new CustomException(443, "转义失败，nickname:"+nickname);
        }
        return ResultFactory.buildSuccessResult(filterUserInfo(
                userInfoService.findByNickname(nickname)));
    }

    private Map<String, String> filterUserInfo(UserInfo userInfo){
        return ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("identityId", String.valueOf(userInfo.getIdentityId()))
                .put("role", String.valueOf(userInfo.getRole()))
                .put("phone", userInfo.getPhone())
                .put("name", userInfo.getName())

                .put("session_key", userInfo.getSessionKey()).build();
    }
}
