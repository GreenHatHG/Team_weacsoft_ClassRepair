package team.weacsoft.user.service;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.UpdateUserInfoDto;

import java.util.Map;

/**
 * @author GreenHatHG
 */

@Component
public class UserInfoUpdateService {

    @Autowired
    private UserInfoSelectService userInfoService;

    public Map<String, String> updateRoleById(String id, int role){
        UserInfoDo userInfo = userInfoService.findById(id);
        userInfo.setRole(role);
        userInfo = userInfoService.save(userInfo);
        return ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("role", String.valueOf(userInfo.getRole())).build();
    }

    public UserInfoDo updateUserInfo(UpdateUserInfoDto dto){
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
        return userInfoService.save(userInfo);
    }


}
