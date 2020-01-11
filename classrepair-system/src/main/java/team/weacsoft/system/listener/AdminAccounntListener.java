package team.weacsoft.system.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.user.domain.Admin;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.service.UserInfoSelectService;

/**
 * @author GreenHatHG
 */
@Slf4j
@Component
public class AdminAccounntListener {

    @Autowired
    private UserInfoSelectService userInfoSelectService;

    @EventListener(ApplicationReadyEvent.class)
    public void checkAdminAccount(){
        long id = 0;
        try{
            id = Long.valueOf(Admin.getRootId());
            userInfoSelectService.findByIdentityId(id);
        }catch (EntityNotFoundException e){
            UserInfoDo userInfoDo = UserInfoDo.builder()
                    .identityId(id)
                    .password(Argon2Util.hash(Admin.getRootPwdFromProperties())).build();
            userInfoDo.setOpenid("");
            userInfoSelectService.save(userInfoDo);
        }catch (NumberFormatException e){
            log.error(e.getMessage());
        }
    }

}
