package team.weacsoft.qatype.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 */

@Getter
@Setter
public class AddQaTypeDto {

    @NotNull
    private Integer qaTypeId;

    /**
     * 顺序，显示的先后顺序
     */
    @NotNull
    private Integer sort;

    /**
     * 显示的标题
     */
    @Size(max = 100)
    @NotBlank
    private String title;

    /**
     * 备注
     */
    @Size(max = 100)
    private String remark = "";
}
