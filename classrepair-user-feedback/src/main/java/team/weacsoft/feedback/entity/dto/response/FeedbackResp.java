package team.weacsoft.feedback.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @ClassName FeedBackResp
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 18:11
 */
@Data
@AllArgsConstructor
public class FeedbackResp {

    private int Id;

    private String message;

}
