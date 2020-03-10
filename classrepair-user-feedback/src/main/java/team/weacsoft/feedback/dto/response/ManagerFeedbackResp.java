package team.weacsoft.feedback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @ClassName ManagerFeedbackResp
 * @Author 魔法はまだ解けない
 * @date 2020.03.10 13:36
 */
@Data
public class ManagerFeedbackResp{

    int id;

    String orderPhone;

    String state;

    String question;
}
