package team.weacsoft.system.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import team.weacsoft.common.persistence.Admin;
import team.weacsoft.common.utils.Argon2Util;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.service.impl.UserInfoServiceImpl;

/**
 * @author GreenHatHG
 */
@Slf4j
@Component
public class AdminAccounntListener {

    private Admin admin;
    private UserInfoServiceImpl userInfoService;

    public AdminAccounntListener(Admin admin, UserInfoServiceImpl userInfoService) {
        this.admin = admin;
        this.userInfoService = userInfoService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void checkAdminAccount(){
        long id = 0;
        try{
            id = Long.parseLong(admin.getRootIdentityId());
            admin.setRootId(userInfoService.getOne(new QueryWrapper<UserInfo>().eq("identity_id", id)).getId());
        }catch (NullPointerException e){
            UserInfo userInfo = UserInfo.builder()
                    .identityId(id)
                    .role(5)
                    .password(Argon2Util.hash(admin.getRootPwd())).build();
            userInfo.setOpenid("");
            userInfoService.save(userInfo);
            admin.setRootId(userInfo.getId());
        }
    }

}
