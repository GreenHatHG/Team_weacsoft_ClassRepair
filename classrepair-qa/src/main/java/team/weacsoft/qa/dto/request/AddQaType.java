package team.weacsoft.qa.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author GreenHatHG
 * @since 2020-01-27
 */

@Data
public class AddQaType {
    @NotNull
    @Min(0) @Max(120)
    private Integer sort;
    @Size(max = 200)
    @NotBlank
    private String title;
    private String remark = "";
}
