package team.weacsoft.log.controller2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.log.dto.reponse.FindRepairLogDto;
import team.weacsoft.log.dto.reponse.SearchRepairLogDto;
import team.weacsoft.log.entity.RepairLog;
import team.weacsoft.log.service.IRepairLogService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 报修表 前端控制器
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-02-14
 */
@Validated
@RestController
@RequestMapping("/api/v2/repair_log")
public class RepairLogController {

    private IRepairLogService repairLogService;

    @Autowired
    public RepairLogController(IRepairLogService repairLogService) {
        this.repairLogService = repairLogService;
    }

    /**
     * 提交订单维护日志
     */
    @Log(module = "订单维护日志", operation = "提交订单维护日志")
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")
    @PostMapping("/actions/add")
    public ResponseEntity<ApiResp> addRepairLog(@Validated @RequestBody RepairLog repairLog){
        return ApiResp.ok(repairLogService.addRepairLog(repairLog));
    }

    /**
     * 搜索维护日志
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/{repair_item_id}/actions/search")
    public ResponseEntity<ApiResp> addRepairLog(@NotBlank @Size(max = 100) @PathVariable(name = "repair_item_id") String repairItemId){
        SearchRepairLogDto searchRepairLogDto = repairLogService.searchLog(repairItemId);
        if (searchRepairLogDto==null){
            return ApiResp.ok(ApiResp.error(400,"无符合条件结果"));
        }
        return ApiResp.ok(searchRepairLogDto);
    }

    /**
     * 查询订单维护日志
     */
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")
    @GetMapping("/{repair_item_id}/info")
    public ResponseEntity<ApiResp> findRepairLog(@NotBlank @Size(max = 100) @PathVariable(name = "repair_item_id") String repairItemId){
        FindRepairLogDto repairLog = repairLogService.findRepairLog(repairItemId);
        ApiResp apiResp;
        if(repairLog==null){
            return ApiResp.ok(ApiResp.error(400,"查询失败"));
        }
        return ApiResp.ok(repairLog);
    }
}
