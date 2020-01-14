package team.weacsoft.user.service;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.UnauthorizedException;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.UpdateUserInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author GreenHatHG
 */

@Component
public class UserInfoUpdateService {

    @Autowired
    private UserInfoSelectService userInfoService;

    @Autowired
    private JwtUtil jwtUtil;

    public Map<String, String> updateRoleById(String id, int role){
        UserInfoDo userInfo = userInfoService.findById(id);
        userInfo.setRole(role);
        userInfo = userInfoService.save(userInfo);
        return ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("role", String.valueOf(userInfo.getRole())).build();
    }

    public UserInfoDo updateUserInfo(UpdateUserInfoDto dto, HttpServletRequest request){
        String nowId = jwtUtil.getIdFromHttpServletRequest(request);;
        UserInfoDo userInfo = null;
        if(StringUtils.isNotBlank(dto.getId())){
            int nowRole = userInfoService.findById(nowId).getRole();
            //如果 要修改的那个人的权限 大于等于 现在操作的人的权限 或者 现在操作的人不是管理员 则抛出异常
            if(userInfoService.findById(dto.getId()).getRole() >= nowRole && nowRole < 3){
                throw new UnauthorizedException("权限不足");
            }
            userInfo = userInfoService.findById(dto.getId());
        }else{
            userInfo = userInfoService.findById(nowId);
        }
        if(StringUtils.isNotBlank(dto.getName())){
            userInfo.setName(dto.getName());
        }
        if(StringUtils.isNotBlank(dto.getAvatar())){
            userInfo.setAvatar(dto.getAvatar());
        }
        if(StringUtils.isNotBlank(dto.getPhone())){
            userInfo.setPhone(dto.getPhone());
        }
        if(StringUtils.isNotBlank(dto.getNickname())){
            userInfo.setNickname(dto.getNickname());
        }
        if(dto.getIdentityId() != null){
            userInfo.setIdentityId(dto.getIdentityId());
        }
        return userInfoService.save(userInfo);
    }


}
