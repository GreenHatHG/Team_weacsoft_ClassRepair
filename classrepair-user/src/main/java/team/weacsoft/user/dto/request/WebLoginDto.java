package team.weacsoft.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
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
    @Min(1)
    private Long account;

    @NotBlank
    @Size(max = 100)
    private String pwd;
}
