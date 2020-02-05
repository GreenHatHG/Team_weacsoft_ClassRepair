package team.weacsoft.repair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.repair.dto.common.CommonRepairItemDto;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.mapper.RepairItemMapper;
import team.weacsoft.repair.service.IRepairItemStateService;

import javax.servlet.http.HttpServletRequest;

/**
 * 优化余地：查表需要LEFT JOIN两次user_info，可以把用户信息放缓存，然后查缓存去得到name
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public class RepairItemStateServiceImpl extends ServiceImpl<RepairItemMapper, RepairItem> implements IRepairItemStateService {

    @Override
    public Page<CommonRepairItemDto> getAllMissedOrder(PageRequest pageRequest) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest), null, 1, null);
    }

    @Override
    public Page<CommonRepairItemDto> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                JwtUtil.getIdFromRequest(request), 2, null);
    }

    @Override
    public Page<CommonRepairItemDto> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                JwtUtil.getIdFromRequest(request), 3, null);
    }

    @Override
    public Page<CommonRepairItemDto> getOtherAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                null, 2, JwtUtil.getIdFromRequest(request));
    }

    @Override
    public Page<CommonRepairItemDto> getOtherAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                null, 3, JwtUtil.getIdFromRequest(request));
    }


}