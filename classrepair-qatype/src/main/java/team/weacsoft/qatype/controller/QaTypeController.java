package team.weacsoft.qatype.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.qatype.service.QaTypeService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 故障表管理
 */
@RestController
@Validated
@Slf4j
@RequestMapping("/public")
public class QaTypeController {

    @Autowired
    private QaTypeService qaTypeService;

    /**
     * 获取故障分类列表
     * @return
     */
    @GetMapping("/qa_types")
    public ResponseEntity<ApiResp> getAll() {
        return ApiResp.ok(qaTypeService.getAll());
    }

    /**
     * 某分类下的所有常见问题
     * @param id
     * @return
     */
    @GetMapping("/qa_types/id")
    public ResponseEntity<ApiResp> findById(@RequestParam @NotBlank @Size(max = 100) String id){
        return ApiResp.ok(qaTypeService.findById(id));
    }

}
