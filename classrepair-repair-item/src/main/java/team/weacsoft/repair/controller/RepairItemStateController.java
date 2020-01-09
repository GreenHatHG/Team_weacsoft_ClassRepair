package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.repair.service.RepairItemStateService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author GreenHatHG
 * @menu 订单管理
 */

//todo 可以重构
@RestController
@RequestMapping(value="/repair_item")
@Validated
public class RepairItemStateController {

    @Autowired
    private RepairItemStateService repairItemStateService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取所有的未接订单
     * @param pageable 分页 size,page,sort(正序)，sort-dsc(倒序)
     * @return
     */
    @GetMapping("/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrder(Pageable pageable){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemStateService.getAllMissedOrder(pageable),
                "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得我的所有未处理订单
     * @param pageable 分页 size,page,sort(正序)，sort-dsc(倒序)
     * @return
     */
    @GetMapping("/my/missed_orders")
    public ResponseEntity<ApiResp> getMyAllMissedOrders(Pageable pageable, HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemStateService.getMyAllMissedOrders(
                jwtUtil.getId(jwtUtil.getJwtFromRequest(request)), pageable),
                "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得我的所有已处理订单
     * @param pageable 分页 size,page,sort(正序)，sort-dsc(倒序)
     * @return
     */
    @GetMapping("/my/processed_orders")
    public ResponseEntity<ApiResp> getMyAllProcessedOrders(Pageable pageable, HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemStateService.getMyAllProcessedOrders(
                jwtUtil.getId(jwtUtil.getJwtFromRequest(request)), pageable),
                "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得他人所有未处理订单
     * @return
     */
    @GetMapping("/id/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrdersById(@RequestParam @NotBlank @Size(max = 100)String id,
                                                          Pageable pageable, HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemStateService.getMyAllMissedOrders(
                id, pageable), "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得他人所有已处理订单
     * @return
     */
    @GetMapping("/id/processed_orders")
    public ResponseEntity<ApiResp> getAllProcessedOrdersById(@RequestParam @NotBlank @Size(max = 100) String id,
                                                             Pageable pageable, HttpServletRequest request){
        return ApiResp.ok(JsonUtil.entityExclude(repairItemStateService.getMyAllProcessedOrders(
                id, pageable), "deleteTime", "id", "receiverUserId"));
    }

}
