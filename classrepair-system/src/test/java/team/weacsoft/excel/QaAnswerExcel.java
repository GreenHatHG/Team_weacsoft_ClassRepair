package team.weacsoft.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author GreenHatHG
 * @since 2020-01-27
 */

@Data
public class QaAnswerExcel {
    @ExcelProperty("menuid")
    private Integer qaTypeId;
    @ExcelProperty("sort")
    private Integer sort;
    @ExcelProperty("question")
    private String question;
    @ExcelProperty("answer")
    private String answerPublic;
    @ExcelProperty("answer_repair")
    private String answerPrivate;
}
