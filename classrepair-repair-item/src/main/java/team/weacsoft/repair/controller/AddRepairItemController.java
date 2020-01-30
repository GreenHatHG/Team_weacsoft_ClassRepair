package team.weacsoft.repair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.impl.AddRepairItemServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GreenHatHG
 * @since 2020-01-30
 */

@RestController
@RequestMapping(value="/repair_item")
@Validated
public class AddRepairItemController {

    private AddRepairItemServiceImpl addRepairItemService;

    @Autowired
    public AddRepairItemController(AddRepairItemServiceImpl addRepairItemService) {
        this.addRepairItemService = addRepairItemService;
    }

    /**
     * 用户报修
     */
    @PreAuthorize("hasAnyRole('1', '2', '3', '4', '5')")
    @Log(module = "订单管理", operation = "用户增加报修单")
    @PostMapping("")
    public ResponseEntity<ApiResp> addOrderItem(@Validated @RequestBody RepairItem repairItemDto,
                                                HttpServletRequest request){
        RepairItem repairItem = addRepairItemService.getRepairItem(repairItemDto, request);
        addRepairItemService.websocket(repairItem);
        return ApiResp.ok(repairItem);
    }
}
