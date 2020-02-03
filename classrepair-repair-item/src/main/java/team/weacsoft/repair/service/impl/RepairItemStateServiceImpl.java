package team.weacsoft.repair.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.repair.dto.reponse.GetAllMissedOrderDto;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.mapper.RepairItemMapper;
import team.weacsoft.repair.service.IRepairItemStateService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public class RepairItemStateServiceImpl extends ServiceImpl<RepairItemMapper, RepairItem> implements IRepairItemStateService {

    @Override
    public Page<GetAllMissedOrderDto> getAllMissedOrder(PageRequest pageRequest) {
        return (Page<GetAllMissedOrderDto>) baseMapper.getAllMissedOrder(PageUtil.getPage(pageRequest));
    }

    @Override
    public Page<RepairItem> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return page(PageUtil.getPage(pageRequest),
                new QueryWrapper<RepairItem>().eq("state", 1).eq("orderer",
                            JwtUtil.getIdFromRequest(request)));
    }

    @Override
    public Page<RepairItem> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return page(PageUtil.getPage(pageRequest),
                new QueryWrapper<RepairItem>().eq("state", 3).eq("orderer",
                        JwtUtil.getIdFromRequest(request)));
    }

    @Override
    public Page<RepairItem> getAllMissedOrdersById(PageRequest pageRequest, String id) {
        return page(PageUtil.getPage(pageRequest),
                new QueryWrapper<RepairItem>().eq("state", 1).eq("orderer", id));
    }

    @Override
    public Page<RepairItem> getAllProcessedOrdersById(PageRequest pageRequest, String id) {
        return page(PageUtil.getPage(pageRequest),
                new QueryWrapper<RepairItem>().eq("state", 3).eq("orderer", id));
    }

}