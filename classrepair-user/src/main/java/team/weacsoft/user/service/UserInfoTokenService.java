package team.weacsoft.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.GetUserInfoByTokenResp;

/**
 * @author GreenHatHG
 */

@Component
public class UserInfoTokenService {

    @Autowired
    private UserInfoSelectService userInfoSelectService;

    @Autowired
    private JwtUtil jwtUtil;

    public GetUserInfoByTokenResp getUserInfoByTokenResp(String token){
        UserInfoDo userInfoDo = userInfoSelectService.findById(jwtUtil.getId(token));

        String identity = null;
        //2-维护人员,3-课室团队负责人 4-老师，5-超级管理员
        switch (userInfoDo.getRole()){
            case 2:
                identity = "维护人员"; break;
            case 3:
                identity = "课室团队负责人"; break;
            case 4:
                identity = "老师"; break;
            case 5:
                identity = "超级管理员"; break;
            default:
                break;
        }

        return GetUserInfoByTokenResp.builder()
                .avatar(userInfoDo.getAvatar())
                .identity(identity)
                .phone(userInfoDo.getPhone())
                .role(userInfoDo.getRole())
                .name(userInfoDo.getName()).build();
    }
}
