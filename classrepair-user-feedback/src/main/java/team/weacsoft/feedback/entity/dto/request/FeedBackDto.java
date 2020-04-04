package team.weacsoft.feedback.entity.dto.request;

import com.alibaba.excel.annotation.ExcelProperty;
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
    @ExcelProperty(value = "")
    private String repairItemId;
    /**
     * 电话号码（非必填）
     */
    private Integer orderPhone;

    /**
     * 描述（必填）
     */
    @NotBlank
    private String content;
}
