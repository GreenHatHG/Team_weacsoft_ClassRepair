package team.weacsoft.feedback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @ClassName FeedBackResp
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 18:11
 */
@Data
@AllArgsConstructor
public class FeedbackResp {

    int feedbackId;

    String message;
}
