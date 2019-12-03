package team.weacsoft.classrepair.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.UserInfo;
import team.weacsoft.classrepair.commons.exception.DataBaseException;
import team.weacsoft.classrepair.commons.exception.NotFoundException;
import team.weacsoft.classrepair.repository.UserInfoRepository;

/**
 * @author GreenHatHG
 */

@Component
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
//    private OperationLogService operationLogService;

    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    public void save(UserInfo userInfo){
        try{
            userInfoRepository.save(userInfo);
        }catch (Exception e){
            log.error("UserInfoService", e);
//            operationLogService.addLog("", EventEnum.Login.event,
//                    EventEnum.Login_FAILED.event+"->"+"保存用户信息失败");
            throw new DataBaseException("保存用户信息失败 -->"+ e.getMessage());
        }
    }

    public UserInfo findByOpenid(String openid){
        return userInfoRepository.findByOpenid(openid);
    }

    public UserInfo findByOpenIdAndCheck(String openid, String code){
        UserInfo userInfo = userInfoRepository.findByOpenid(openid);
        if(userInfo == null){
            throw new NotFoundException("找不到此用户，code -->" + code);
        }
        return userInfo;
    }

    public UserInfo findByIdentityId(long identityId){
        UserInfo userInfo = userInfoRepository.findByIdentityId(identityId);
        if(userInfo == null){
            throw new NotFoundException("找不到此用户，identityId -->" + identityId);
        }
        return userInfo;
    }

}
