package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.repair.service.RepairItemStateService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GreenHatHG
 * @menu 订单管理
 */
// todo 分页
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
     * @return
     */
    @GetMapping("/missed_orders")
    public ResponseEntity<ApiResp> getAllMissedOrder(){
        return ApiResp.ok(JsonUtil.arrayExclude(repairItemStateService.getAllMissedOrder(),
                "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得我的所有未处理订单
     * @return
     */
    @GetMapping("/my/missed_orders")
    public ResponseEntity<ApiResp> getMyAllMissedOrders(HttpServletRequest request){
        return ApiResp.ok(JsonUtil.arrayExclude(repairItemStateService.getMyAllMissedOrders(
                jwtUtil.getId(jwtUtil.getJwtFromRequest(request))), "deleteTime", "id", "receiverUserId"));
    }

    /**
     * 获得我的所有已处理订单
     * @return
     */
    @GetMapping("/my/processed_orders")
    public ResponseEntity<ApiResp> getAllMyProcessedOrders(HttpServletRequest request){
        return ApiResp.ok(JsonUtil.arrayExclude(repairItemStateService.getMyAllProcessedOrders(
                jwtUtil.getId(jwtUtil.getJwtFromRequest(request))), "deleteTime", "id", "receiverUserId"));
    }
}
