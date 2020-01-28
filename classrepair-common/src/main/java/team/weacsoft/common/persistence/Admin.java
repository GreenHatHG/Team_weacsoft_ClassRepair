package team.weacsoft.common.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 */

@Component
@Getter
@Setter
public class Admin {

    @Value("${classrepair.root.pwd}")
    private String rootPwd;

    @Value("${classrepair.root.identityId}")
    private String rootIdentityId;

    private Integer rootId;

}
