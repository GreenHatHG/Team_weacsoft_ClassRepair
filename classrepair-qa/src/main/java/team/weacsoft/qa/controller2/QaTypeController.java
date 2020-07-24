package team.weacsoft.qa.controller2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.qa.dto.common.QaTypeAnswer;
import team.weacsoft.qa.dto.request.AddQaType;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.IQaTypeService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 故障表管理
 * @author GreenHatHG
 * @since 2020-01-27
 */
@Validated
@Slf4j
@RestController
@RequestMapping("/api/v2")
public class QaTypeController {

    private IQaTypeService qaTypeService;

    @Autowired
    public QaTypeController(IQaTypeService qaTypeService) {
        this.qaTypeService = qaTypeService;
    }

    /**
     * 获得某分类下的所有常见问题
     * @param qaTypeId 分类id
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")
    @GetMapping("/qa_types/{id}/qa_answer")
    public ResponseEntity<ApiResp> getQaTypeAnswerById(@PathVariable(value = "id") @NotNull Integer qaTypeId){
        return ApiResp.ok(qaTypeService.getQaTypeAnswerById(qaTypeId));
    }

    /**
     * 获取故障分类列表
     */
    @GetMapping("/public/qa_types")
    public ResponseEntity<ApiResp> getAllQaType(){
        return ApiResp.ok(qaTypeService.getAllQaType());
    }

    /**
     * 添加故障分类
     */
    @Log(module = "故障表管理", operation = "添加故障分类")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PostMapping("/qa_types")
    public ResponseEntity<ApiResp> addQaType(@Validated @RequestBody AddQaType qaType){
        return ApiResp.ok(qaTypeService.addQaType(qaType));
    }

    @Log(module = "故障表管理", operation = "添加某分类下的常见问题")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PostMapping("/qa_types/actions/add_qa_answer")
    public ResponseEntity<ApiResp> addQaAnswer(@Validated @RequestBody List<QaTypeAnswer> qaTypeAnswerList){
        return ApiResp.ok(qaTypeService.addQaAnswer(qaTypeAnswerList));
    }

    /**
     * 删除故障分类
     */
    @Log(module = "故障表管理", operation = "删除故障分类")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PutMapping("/qa_types/drop")
    public ResponseEntity<ApiResp> dropQaTypes(@RequestParam(name = "id") @NotBlank String id){
        QaType drop = qaTypeService.drop(Integer.parseInt(id));
        return ApiResp.ok(drop);
    }

    /**
     * 修改故障分类
     */
    @Log(module = "故障表管理", operation = "修改故障分类")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @PutMapping("/qa_types")
    public ResponseEntity<ApiResp> updateQaTypes(@Validated @RequestBody QaType qaType){
        return ApiResp.ok(qaTypeService.update(qaType));
    }
}
