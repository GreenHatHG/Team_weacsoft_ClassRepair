package team.weacsoft.answer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.answer.domain.dto.AddQaAnswerDto;
import team.weacsoft.answer.service.QaAnswerService;
import team.weacsoft.common.exception.handler.ApiResp;

import java.util.List;

/**
 * 故障表管理
 * @author GreenHatHG
 */
@RestController
@Validated
@Slf4j
public class QaAnswerController {

    @Autowired
    private QaAnswerService qaAnswerService;

    /**
     * 添加某分类下的常见问题
     */
    @PostMapping("/qa_answer/actions/add")
    public ResponseEntity<ApiResp> addQaAnswers(@Validated @RequestBody List<AddQaAnswerDto> addQaAnswerDtoList){
        return ApiResp.ok(qaAnswerService.addQaAnswer(addQaAnswerDtoList));
    }
}
