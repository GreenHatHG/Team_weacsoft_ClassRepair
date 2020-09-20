package team.weacsoft.orders.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.RequestParam;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.orders.entity.RespOrder;

import javax.servlet.http.HttpServletRequest;

public interface GetRepairItemService {
    /**
     * 用户获取订单
     * @param state
     * @param httpServletRequest
     * @return
     */
    Page<RespOrder> UserGetOrders(PageRequest pageRequest,Integer state, HttpServletRequest httpServletRequest);

    /**
     * 管理员获取订单
     * @param state
     * @param self
     * @param httpServletRequest
     * @return
     */
    Page<RespOrder> AdminGetOrders(PageRequest pageRequest,Integer state, Integer self, HttpServletRequest httpServletRequest);
}
