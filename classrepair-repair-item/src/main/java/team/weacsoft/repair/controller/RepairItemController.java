package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.repair.domain.RepairItemDo;
import team.weacsoft.repair.domain.dto.OrderItemDto;
import team.weacsoft.repair.service.RepairItemService;
import team.weacsoft.user.domain.UserInfoDo;
import team.weacsoft.user.service.UserInfoSelectService;

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

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserInfoSelectService userInfoService;

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
        UserInfoDo userInfo = userInfoService.findById(jwtUtil.getId(jwtUtil.getJwtFromRequest(request)));
        RepairItemDo repairItem = RepairItemDo.builder()
                .repairItemId(repairItemService.getRepairItemId())
                .orderUserId(userInfo.getId())
                .classroom(orderItemDto.getClassroom())
                .equipmentType(orderItemDto.getEquipmentType())
                .problem(orderItemDto.getProblem())
                .oderUserPhone(orderItemDto.getOderUserPhone() == null ? userInfo.getPhone() : orderItemDto.getOderUserPhone())
                .build();
        return ApiResp.ok(JsonUtil.entityInclude(repairItemService.save(repairItem),
                "repairItemId", "orderUserId", "classroom", "equipmentType", "problem", "oderUserPhone"));
    }

    /**
     * 维修人员接单
     * @param repair_item_id 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维修人员接单")
    @PostMapping("/actions/order")
    public ResponseEntity<ApiResp> order(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                                         HttpServletRequest request){
        RepairItemDo repairItem = repairItemService.findByRepairItemId(repair_item_id);
        UserInfoDo userInfo = userInfoService.findById(jwtUtil.getId(jwtUtil.getJwtFromRequest(request)));
        if(repairItem.getState() == 2){
            throw new BadRequestException(465, "该单已接，接单人:" +
                    userInfoService.findById(repairItem.getReceiverUserId()).getName());
        }
        repairItem.setReceiverUserId(userInfo.getId());
        repairItem.setState(2);
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.save(repairItem),
                "id", "deleteTime", "openid"));
    }

    //todo 普通人员只能取消自己下的单
    /**
     * 取消报修
     * @param repair_item_id 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "取消报修")
    @PutMapping("/actions/cancel_repair")
    public ResponseEntity<ApiResp> cancelRepair(@RequestParam @NotBlank @Size(max = 100) String repair_item_id,
                                                HttpServletRequest request){
        RepairItemDo repairItem = repairItemService.findByRepairItemId(repair_item_id);
        repairItem.setState(4);
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.save(repairItem),
                "deleteTime", "id"));
    }

    /**
     * 维护人员取消接单
     * @param repair_item_id 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "维护人员取消接单")
    @PutMapping("/actions/maintenance_cancel")
    public ResponseEntity<ApiResp> cancelOrder(@RequestParam @NotBlank @Size(max = 100) String repair_item_id) {
        RepairItemDo repairItem = repairItemService.findByRepairItemId(repair_item_id);
        if(repairItem.getState() != 2){
            throw new BadRequestException(499, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(1);
        repairItem.setReceiverUserId("");
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.save(repairItem),
                "deleteTime", "id"));
    }

    /**
     * 完成报修
     * @param repair_item_id 订单号
     * @return
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "完成报修")
    @PutMapping("/actions/complete")
    public ResponseEntity<ApiResp> completeOrder(@RequestParam @NotBlank @Size(max = 100) String repair_item_id) {
        RepairItemDo repairItem = repairItemService.findByRepairItemId(repair_item_id);
        if(repairItem.getState() != 2){
            throw new BadRequestException(499, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(3);
        return ApiResp.ok(JsonUtil.entityExclude(repairItemService.save(repairItem),
                "deleteTime", "id"));
    }

}