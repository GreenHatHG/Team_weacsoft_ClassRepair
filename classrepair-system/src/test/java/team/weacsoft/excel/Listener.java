package team.weacsoft.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.qa.entity.QaAnswer;
import team.weacsoft.qa.service.IQaAnswerService;
import team.weacsoft.qa.service.impl.QaAnswerServiceImpl;
import team.weacsoft.qa.service.impl.QaTypeServiceImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * @author GreenHatHG
 */

public class Listener extends AnalysisEventListener<QaAnswerExcel> {

    private IQaAnswerService qaAnswerService;

    public Listener(IQaAnswerService qaAnswerService) {
        this.qaAnswerService = qaAnswerService;
    }

    List<QaAnswer> qaAnswerList = new ArrayList<>();

    @Override
    public void invoke(QaAnswerExcel qaAnswerExcel, AnalysisContext analysisContext) {
        QaAnswer qaAnswer = QaAnswer.builder().build();
        JsonUtil.getCopyDto(qaAnswerExcel, qaAnswer);
        if(qaAnswer.getAnswerPrivate() == null){
            qaAnswer.setAnswerPrivate("");
        }
        qaAnswerList.add(qaAnswer);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        qaAnswerService.saveBatch(qaAnswerList);
    }
}
