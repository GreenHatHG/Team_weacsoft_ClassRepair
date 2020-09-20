package team.weacsoft.orders.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.orders.entity.RespOrder;
import team.weacsoft.orders.service.GetRepairItemService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/9/12
 */
@RestController
@RequestMapping("/api/v2/order")
public class UserOrderController {

    @Autowired
    GetRepairItemService getRepairItemService;

    /**
     * 用户获取订单
     * @param state
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @Log(module = "订单管理", operation = "用户获取订单")
    public ResponseEntity<ApiResp> getOrders(PageRequest pageRequest,@RequestParam(required = false) Integer state, HttpServletRequest httpServletRequest){
        Page<RespOrder> iPagePage = getRepairItemService.UserGetOrders(pageRequest,state, httpServletRequest);
        return ApiResp.ok(iPagePage);
    }
}
