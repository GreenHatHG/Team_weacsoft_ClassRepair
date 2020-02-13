package team.weacsoft.repair.controller1;

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
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@RestController
@RequestMapping(value="/api/v1/repair_item")
@Validated
public class RepairItemStateController1 {

    private IRepairItemStateService repairItemStateService;

    @Autowired
    public RepairItemStateController1(IRepairItemStateService repairItemStateService) {
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
     * 管理员-我的待处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/my/missed_orders")
    public ResponseEntity<ApiResp> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getMyAllMissedOrders(pageRequest, request));
    }

    /**
     * 管理员-我的所有已处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/my/processed_orders")
    public ResponseEntity<ApiResp> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getMyAllProcessedOrders(pageRequest, request));
    }

    /**
     * 管理员-他人待处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/id/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrdersById(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getOtherAllMissedOrders(pageRequest, request));
    }

    /**
     * 获得他人所有已处理订单
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/id/processed_orders")
    public ResponseEntity<ApiResp> getAllProcessedOrdersById(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getOtherAllProcessedOrders(pageRequest, request));
    }

    /**
     * 用户侧-获取我的待处理订单
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/user/missed_orders")
    public ResponseEntity<ApiResp> getUserAllMissedOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getUserAllMissedOrders(pageRequest, request));
    }

    /**
     * 用户侧-获取我的所有报修单
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/user/orders")
    public ResponseEntity<ApiResp> getUserAllOrders(PageRequest pageRequest, HttpServletRequest request){
        return ApiResp.ok(repairItemStateService.getUserAllOrders(pageRequest, request));
    }

    /**
     * 模糊搜索订单，订单号||下单人名字||接单人学号
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @GetMapping("/actions/search")
    public ResponseEntity<ApiResp> searchRepairItem(PageRequest pageRequest,
                                                    @RequestParam(value = "repair_item_id", required = false) @Size(max=100) String repairItemId,
                                                    @RequestParam(value = "orderer_name", required = false) @Size(max=100)String ordererName,
                                                    @RequestParam(value = "receiver_identity_id", required = false) Integer receiverIdentityId){
        return ApiResp.ok(repairItemStateService.searchRepairItem(pageRequest, repairItemId, ordererName, receiverIdentityId));
    }
}
