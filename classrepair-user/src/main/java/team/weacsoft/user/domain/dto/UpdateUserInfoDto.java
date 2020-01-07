package team.weacsoft.user.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class UpdateUserInfoDto {

    /**
     * 要修改的那个人的id
     */
    @Size(max = 100)
    @NotBlank
    String id;

    @Size(max = 100)
    String name = "";

    @Size(max = 100)
    String avatar = "";

    @Size(max = 100)
    String phone = "";

    @Size(max = 100) String nickname = "";

    Long identityId = (long)0;
}
