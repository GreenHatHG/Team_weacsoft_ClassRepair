package team.weacsoft.invitation.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import team.weacsoft.common.exception.BadRequestException;

/**
 * @author GreenHatHG
 */

@Aspect
@Component
@Slf4j
public class FeaturesService {

    private static boolean open = false;

    public static String isOpen() {
        return open?"功能正常开启":"功能已经关闭";
    }

    public static void setOpen(boolean open) {
        FeaturesService.open = open;
    }

    @Before("execution(* team.weacsoft.invitation.controller.InvitationController.*(..))")
    public void control(){
        if(!open){
            throw new BadRequestException(423, "该功能暂时无法访问");
        }
    }
}
