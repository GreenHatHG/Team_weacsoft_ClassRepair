package team.weacsoft.classrepair.commons.control;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.commons.exception.CustomException;

/**
 * @author GreenHatHG
 */

@Aspect
@Component
@Slf4j
public class InvitationControl {

    private static boolean open = false;

    public static String isOpen() {
        return open?"功能正常开启":"功能已经关闭";
    }

    public static void setOpen(boolean open) {
        InvitationControl.open = open;
    }

    @Before("execution(* team.weacsoft.classrepair.controller.InvitationController.*(..))")
    public void control(){
        if(!open){
            throw new CustomException(423, "该功能暂时无法访问");
        }
    }

}
