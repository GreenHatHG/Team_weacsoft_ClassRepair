package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.repair.domain.dto.OrderItemDto;
import team.weacsoft.repair.service.RepairItemService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 订单管理
 **/

//todo 可以精简，idea重构
@RestController
@RequestMapping(value="/repair_item")
@Validated
public class RepairItemController {

    @Autowired
    private RepairItemService repairItemService;

    /**
     * 用户报修接口
     * @param orderItemDto 用户报修信息
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "用户增加报修单")
    @PostMapping("")
    public ResponseEntity<ApiResp> addOrderItem(@Validated @RequestBody OrderItemDto orderItemDto,
                                                HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityInclude(repairItemService.addOrderItem(orderItemDto, request),
                "repairItemId", "orderUserId", "classroom", "equipmentType", "problem", "oderUserPhone"));
    }

    /**
     * 维修人员接单
     * @param repairItemId 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维修人员接单")
    @PostMapping("/actions/order")
    public ResponseEntity<ApiResp> order(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                         HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.order(repairItemId, request),
                "id", "deleteTime", "openid"));
    }

    /**
     * 取消报修
     * @param repairItemId 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "取消报修")
    @PutMapping("/actions/cancel_repair")
    public ResponseEntity<ApiResp> cancelRepair(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.cancelRepair(repairItemId, request),
                "deleteTime", "id"));
    }

    /**
     * 维护人员取消接单
     * @param repairItemId 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维护人员取消接单")
    @PutMapping("/actions/maintenance_cancel")
    public ResponseEntity<ApiResp> cancelReceive(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                 HttpServletRequest request) {
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.cancelReceive(repairItemId, request),
                "deleteTime", "id"));
    }

    /**
     * 完成报修
     * @param repairItemId 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "完成报修")
    @PutMapping("/actions/complete")
    public ResponseEntity<ApiResp> completeOrder(@RequestParam(name = "repair_item_id") @NotBlank @Size(max = 100) String repairItemId,
                                                 HttpServletRequest request) {
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.completeOrder(repairItemId, request),
                "deleteTime", "id"));
    }

}