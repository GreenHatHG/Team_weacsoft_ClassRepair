package team.weacsoft.user.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */

@Component
public class Admin {

    private static String rootId;

    private static String rootPwd;

    @Value("${classrepair.root.id}")
    public void setRootId(String rootId) {
        Admin.rootId = rootId;
    }

    @Value("${classrepair.root.pwd}")
    public void setRootPwd(String rootPwd) {
        Admin.rootPwd = rootPwd;
    }


    public static String getRootId() {
        return rootId;
    }

    public static String getRootPwdFromProperties() {
        return rootPwd;
    }

}
