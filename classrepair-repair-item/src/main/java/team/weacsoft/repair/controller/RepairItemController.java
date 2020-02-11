package team.weacsoft.repair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 报修表 前端控制器
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
//todo 实现幂等性
@RestController
@RequestMapping(value="/repair_item")
@Validated
public class RepairItemController {

    private ConcurrentHashMap<String , BaseUpdateRepairItemService> map = new ConcurrentHashMap<>();

    @Autowired
    public RepairItemController(List<BaseUpdateRepairItemService> list) {
        for (BaseUpdateRepairItemService base : list) {
            map.put(base.getType(), base);
        }
    }

    /**
     * 维修人员接单
     * @param repairItemId 订单号
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维修人员接单")
    @PutMapping("/actions/order")
    public ResponseEntity<ApiResp> receive(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                         HttpServletRequest request){
        return ApiResp.ok(map.get("Receive").getRepairItem(repairItemId, request));
    }

    /**
     * 取消报修
     * @param repairItemId 订单号
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "取消报修")
    @PutMapping("/actions/cancel_repair")
    public ResponseEntity<ApiResp> cancelRepair(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                HttpServletRequest request){
        return ApiResp.ok(map.get("CancelRepair").getRepairItem(repairItemId, request));
    }

    /**
     * 维护人员取消接单
     * @param repairItemId 订单号
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维护人员取消接单")
    @PutMapping("/actions/maintenance_cancel")
    public ResponseEntity<ApiResp> cancelReceive(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                 HttpServletRequest request) {
        return ApiResp.ok(map.get("CancelReceive").getRepairItem(repairItemId, request));
    }

    /**
     * 完成报修
     * @param repairItemId 订单号
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "完成报修")
    @PutMapping("/actions/complete")
    public ResponseEntity<ApiResp> completeOrder(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                 HttpServletRequest request) {
        return ApiResp.ok(map.get("Complete").getRepairItem(repairItemId, request));
    }

}
