package team.weacsoft.orders.controller2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.orders.service.GetRepairItemService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/12
 */
@Api(value = "AdministerOrderController",  tags = "订单模块 | 管理员管理订单 LoginController ")
@RestController
@RequestMapping("/api/v2/order")
public class AdministerOrderController {

    @Autowired
    GetRepairItemService getRepairItemService;

    /**
     * 普通人获取订单
     *
     * @param state 订单状态：1待处理，2已查看，3处理中，4已处理，5已取消
     * @param self
     * @return
     */
    @ApiOperation(value="普通人获取订单", notes="")
    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "订单管理", operation = "管理员获取订单")
    public ResponseEntity<ApiResp> getOrders(PageRequest pageRequest, @RequestParam(required = false)  Integer state, @RequestParam(required = false)  Integer self, HttpServletRequest httpServletRequest){
        return ApiResp.ok(getRepairItemService.AdminGetOrders(pageRequest,state,self,httpServletRequest));
    }

}
