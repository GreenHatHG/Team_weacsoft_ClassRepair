package team.weacsoft.answer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.answer.domain.dto.AddQaAnswerDto;
import team.weacsoft.answer.service.QaAnswerService;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JsonUtil;

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
    @Log(module = "故障表管理", operation = "添加某分类下的常见问题")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @PostMapping("/qa_answer/actions/add")
    public ResponseEntity<ApiResp> addQaAnswers(@Validated @RequestBody List<AddQaAnswerDto> addQaAnswerDtoList){
        return ApiResp.ok(JsonUtil.arrayExclude(qaAnswerService.addQaAnswer(addQaAnswerDtoList),
                "createTime", "updateTime", "state", "id"));
    }
}
