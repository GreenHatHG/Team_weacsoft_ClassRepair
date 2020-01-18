package team.weacsoft.answer.domain.dto;

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
public class AddQaAnswerDto {
    /**
     * 目录id，与qa_type表对应
     */
    @Size(max = 100)
    @NotBlank
    private String menuId;

    /**
     * 顺序，在同一目录时显示的先后顺序
     */
    @NotNull
    private Integer sort;

    /**
     * 问题
     */
    @Size(max = 100)
    @NotBlank
    private String question;

    /**
     * 解决方案公开版
     */
    @Size(max = 100)
    @NotBlank
    private String answer;

    /**
     * 解决方案内部版
     */
    @Size(max = 100)
    private String answerRepair = "";

    /**
     * 采纳次数，默认为0
     */
    private int goodNum = 0;
}
