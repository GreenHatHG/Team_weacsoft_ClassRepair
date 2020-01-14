package team.weacsoft.user.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.repository.UserInfoRepository;

import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

/**
 * @author GreenHatHG
 */

@Component
@Slf4j
public class UserInfoSelectService {

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

        return optionalUserInfo.get();
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

    public Page<UserInfoDo> getUserList(Pageable pageable){
        return userInfoRepository.findAll(pageable);
    }

}
