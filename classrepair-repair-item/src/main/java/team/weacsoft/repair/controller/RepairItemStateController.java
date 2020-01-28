package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.repair.service.IRepairItemStateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@RestController
@RequestMapping(value="/repair_item")
@Validated
public class RepairItemStateController {

    private IRepairItemStateService repairItemStateService;

    @Autowired
    public RepairItemStateController(IRepairItemStateService repairItemStateService) {
        this.repairItemStateService = repairItemStateService;
    }

    /**
     * 获取所有的未接订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrder(PageRequest pageRequest){
        return ApiResp.ok(repairItemStateService.getAllMissedOrder(pageRequest));
    }

    /**
     * 获得我的所有未处理订单
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/my/missed_orders")
    public ResponseEntity<ApiResp> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getMyAllMissedOrders(pageRequest, request));
    }

    /**
     * 获得我的所有已处理订单
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/my/processed_orders")
    public ResponseEntity<ApiResp> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getMyAllProcessedOrders(pageRequest, request));
    }

    /**
     * 获得他人所有未处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/id/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrdersById(@RequestParam @NotBlank @Size(max = 100)String id,
                                                          PageRequest pageRequest){
        return ApiResp.ok(repairItemStateService.getAllMissedOrdersById(pageRequest, id));
    }

    /**
     * 获得他人所有已处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/id/processed_orders")
    public ResponseEntity<ApiResp> getAllProcessedOrdersById(@RequestParam @NotBlank @Size(max = 100) String id,
                                                             PageRequest pageRequest){
        return ApiResp.ok(repairItemStateService.getAllProcessedOrdersById(pageRequest, id));
    }
}
