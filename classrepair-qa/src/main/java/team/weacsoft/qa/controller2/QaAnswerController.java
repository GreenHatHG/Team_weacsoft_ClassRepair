package team.weacsoft.qa.controller2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.qa.service.IQaAnswerService;

import javax.validation.constraints.NotNull;

/**
 * 故障表管理
 * @author GreenHatHG
 * @since 2020-01-27
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/api/v2/qa_answer")
public class QaAnswerController {

    private IQaAnswerService qaAnswerService;

    @Autowired
    public QaAnswerController(IQaAnswerService qaAnswerService) {
        this.qaAnswerService = qaAnswerService;
    }

    /**
     * 方案采纳
     */
    @Log(module = "故障表管理", operation = "方案采纳")
    @PutMapping("/{id}/good_num")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    public ResponseEntity<ApiResp> updateGoodNum(@PathVariable @NotNull Integer id){
        return ApiResp.ok(qaAnswerService.updateGoodNum(id));
    }
}