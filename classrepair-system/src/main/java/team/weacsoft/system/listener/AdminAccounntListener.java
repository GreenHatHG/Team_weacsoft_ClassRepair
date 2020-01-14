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

    private Admin admin;
    private UserInfoSelectService userInfoSelectService;

    @Autowired
    public AdminAccounntListener(Admin admin, UserInfoSelectService userInfoSelectService) {
        this.admin = admin;
        this.userInfoSelectService = userInfoSelectService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void checkAdminAccount(){
        long id = 0;
        try{
            id = Long.valueOf(admin.getRootIdentityId());
            admin.setRootId(userInfoSelectService.findByIdentityId(id).getId());
        }catch (EntityNotFoundException e){
            UserInfoDo userInfoDo = UserInfoDo.builder()
                    .identityId(id)
                    .role(5)
                    .password(Argon2Util.hash(admin.getRootPwd())).build();
            userInfoDo.setOpenid("");
            admin.setRootId(userInfoSelectService.save(userInfoDo).getId());
        }catch (NumberFormatException e){
            log.error(e.getMessage());
        }
    }

}
