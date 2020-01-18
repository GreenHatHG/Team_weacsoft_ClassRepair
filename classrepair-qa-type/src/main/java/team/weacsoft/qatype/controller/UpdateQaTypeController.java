package team.weacsoft.qatype.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.qatype.domain.dto.AddQaTypeDto;
import team.weacsoft.qatype.service.UpdateQaTypeService;

/**
 * 故障表管理
 * @author GreenHatHG
 */

@RestController
@Validated
@Slf4j
@RequestMapping("/qa_types")
public class UpdateQaTypeController {

    @Autowired
    private UpdateQaTypeService updateQaTypeService;

    /**
     * 添加故障分类
     */
    @Log(module = "故障表管理", operation = "添加故障分类")
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @PostMapping("/actions/add")
    public ResponseEntity<ApiResp> addQaType(@Validated @RequestBody AddQaTypeDto addQaTypeDto){
        return ApiResp.ok(JsonUtil.entityExclude(updateQaTypeService.addQaType(addQaTypeDto),
                "createTime", "updateTime", "id", "state"));
    }

}
