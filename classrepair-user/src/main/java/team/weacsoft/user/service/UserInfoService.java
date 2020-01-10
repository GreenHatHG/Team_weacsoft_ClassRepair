package team.weacsoft.user.service;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.domain.dto.UpdateUserInfoDto;
import team.weacsoft.user.repository.UserInfoRepository;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfoDo save(UserInfoDo userInfo){
        return userInfoRepository.save(userInfo);
    }

    public UserInfoDo findByOpenid(String openid){
        return userInfoRepository.findByOpenid(openid);
    }

    public UserInfoDo findByOpenIdAndCheck(String openid, String code){
        UserInfoDo userInfo = userInfoRepository.findByOpenid(openid);
        if(userInfo == null){
            MDC.put("userTableId", "找不到用户");
            throw new EntityNotFoundException(UserInfoDo.class, "openid", openid);
        }
        return userInfo;
    }

    public UserInfoDo findByIdentityId(long identityId){
        UserInfoDo userInfo = userInfoRepository.findByIdentityId(identityId);
        if(userInfo == null){
            throw new EntityNotFoundException(UserInfoDo.class, "identityId", String.valueOf(identityId));
        }
        return userInfo;
    }

    public UserInfoDo findById(String id){
        Optional<UserInfoDo> optionalUserInfo = userInfoRepository.findById(id);
        if(!optionalUserInfo.isPresent()){
            throw new EntityNotFoundException(UserInfoDo.class, "id", id);
        }
        UserInfoDo userInfo = optionalUserInfo.get();

        return userInfo;
    }

    public List<UserInfoDo> findByName(String name){
        List<UserInfoDo> userInfos = userInfoRepository.findByName(name);
        if(userInfos.size() == 0){
            throw new EntityNotFoundException(UserInfoDo.class, "name", name);
        }
        return userInfos;
    }

    public UserInfoDo findByNickname(String nickname){
        try{
            nickname = URLDecoder.decode(nickname, "utf-8");
        }catch (Exception e){
            throw new BadRequestException(432, "转义失败：nickname:"+nickname);
        }
        UserInfoDo userInfo = userInfoRepository.findByNickname(nickname);
        if(userInfo == null){
            throw new EntityNotFoundException(UserInfoDo.class, "nickname", nickname);
        }
        return userInfo;
    }

    public Map<String, String> updateRoleById(String id, int role){
        UserInfoDo userInfo = findById(id);
        userInfo.setRole(role);
        userInfo = save(userInfo);
        return ImmutableMap.<String, String> builder()
                .put("id", userInfo.getId())
                .put("role", String.valueOf(userInfo.getRole())).build();
    }

    public UserInfoDo updateUserInfo(UpdateUserInfoDto dto){
        UserInfoDo userInfo = findById(dto.getId());
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
        return save(userInfo);
    }
}
