package team.weacsoft.classrepair.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;
import team.weacsoft.classrepair.service.QaTypeService;

/**
 * @author GreenHatHG
 * @menu 故障表管理
 */
@RestController
@Validated
@Slf4j
public class QaTypeController {

    @Autowired
    private QaTypeService qaTypeService;

    /**
     * 获取故障分类列表
     * @return
     */
    @GetMapping("/qa_types")
    public Result getAll() {
        return ResultFactory.buildSuccessResult(qaTypeService.getAll());
    }

}
