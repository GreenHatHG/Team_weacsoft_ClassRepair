package team.weacsoft.qa.dto.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author GreenHatHG
 * @since 2020-01-27
 */

@Data
public class QaTypeAnswer {
    private Integer questionid;
    @NotNull
    private Integer qaTypeId;
    @NotNull
    private Integer sort;
    @NotBlank
    private String question;
    private String answerPublic = "";
    private String answerPrivate = "";
    private Integer goodNum = 0;
}