package team.weacsoft.feedback.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Description 参数
 * @ClassName FeedBackDto
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 17:17
 */
@Data
@NoArgsConstructor
public class FeedBackDto {
    /**
     * 电话号码（非必填）
     */
    Integer OrderPhone;

    /**
     * 描述（必填）
     */
    @NotBlank
    String content;
}
