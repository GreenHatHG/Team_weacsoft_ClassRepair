package team.weacsoft.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class WebLoginDto {

    /**
     * 用户名
     */
    @NotNull
    private Long account;

    @NotBlank
    @Size(max = 100)
    private String pwd;
}
