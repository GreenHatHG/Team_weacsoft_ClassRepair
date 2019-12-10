package team.weacsoft.classrepair.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.exception.DataBaseException;
import team.weacsoft.classrepair.commons.exception.NotFoundException;
import team.weacsoft.classrepair.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author GreenHatHG
 */

@Component
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    public void save(UserInfo userInfo){
        try{
            userInfoRepository.save(userInfo);
        }catch (Exception e){
            throw new DataBaseException("保存用户信息失败 -->"+ e.getMessage());
        }
    }

    public UserInfo findByOpenid(String openid){
        return userInfoRepository.findByOpenid(openid);
    }

    public UserInfo findByOpenIdAndCheck(String openid, String code){
        UserInfo userInfo = userInfoRepository.findByOpenid(openid);
        if(userInfo == null){
            MDC.put("userTableId", "找不到用户");
            throw new NotFoundException("找不到此用户，code:" + code);
        }
        MDC.put("userTableId", userInfo.getId());
        return userInfo;
    }

    public UserInfo findByIdentityId(long identityId){
        UserInfo userInfo = userInfoRepository.findByIdentityId(identityId);
        if(userInfo == null){
            throw new NotFoundException("找不到此用户，identityId:" + identityId);
        }
        return userInfo;
    }

    public UserInfo findById(String id){
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(id);
        if(!optionalUserInfo.isPresent()){
            throw new NotFoundException("找不到此用户，id:" + id);
        }
        UserInfo userInfo = optionalUserInfo.get();
        MDC.put("userTableId", userInfo.getId());
        return userInfo;
    }

    public List<UserInfo> findByName(String name){
        List<UserInfo> userInfos = userInfoRepository.findByName(name);
        if(userInfos.size() == 0){
            throw new NotFoundException("找不到此用户，name:" + name);
        }
        return userInfos;
    }

    public UserInfo findByNickname(String nickname){
        UserInfo userInfo = userInfoRepository.findByNickname(nickname);
        if(userInfo == null){
            throw new NotFoundException("找不到此用户，nickname:" + nickname);
        }
        return userInfo;
    }

}
