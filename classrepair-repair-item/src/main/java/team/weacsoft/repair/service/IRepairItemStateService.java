package team.weacsoft.repair.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.repair.dto.reponse.GetAllMissedOrderDto;
import team.weacsoft.repair.entity.RepairItem;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 报修表 服务类
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface IRepairItemStateService extends IService<RepairItem> {

    /**
     * 获取所有的未接订单
     */
    Page<GetAllMissedOrderDto> getAllMissedOrder(PageRequest pageRequest);

    /**
     * 获得我的所有未处理订单
     */
    Page<RepairItem> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 获得我的所有已处理订单
     */
    Page<RepairItem> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request);

    /**
     * 获得他人所有未处理订单
     */
    Page<RepairItem> getAllMissedOrdersById(PageRequest pageRequest, String id);

    /**
     * 获得他人所有已处理订单
     */
    Page<RepairItem> getAllProcessedOrdersById(PageRequest pageRequest, String id);
}
