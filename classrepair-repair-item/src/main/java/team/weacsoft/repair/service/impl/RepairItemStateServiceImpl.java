package team.weacsoft.repair.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.repair.dto.request.CommonRepairItemDto;
import team.weacsoft.repair.dto.request.ExcelRepariItemDto;
import team.weacsoft.repair.dto.response.StatisticsFromEquipment;
import team.weacsoft.repair.entity.OrderSearchEntity;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.mapper.RepairItemMapper;
import team.weacsoft.repair.service.IRepairItemStateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 优化余地：查表需要LEFT JOIN两次user_info，可以把用户信息放缓存，然后查缓存去得到name
 *
 * @author GreenHatHG、魔法はまだ解けない
 * @since 2020-01-28
 */

@Service
public class RepairItemStateServiceImpl extends ServiceImpl<RepairItemMapper, RepairItem> implements IRepairItemStateService {

    @Override
    public Page<CommonRepairItemDto> getAllOrders(PageRequest pageRequest) {
        return (Page<CommonRepairItemDto>) baseMapper.getAllRepairItem(PageUtil.getPage(pageRequest));
    }

    @Override
    public Page<CommonRepairItemDto> getAllMissedOrder(PageRequest pageRequest) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(
                PageUtil.getPage(pageRequest), null, RepairItemStateEnum.PENDING.getState(), null);
    }

    @Override
    public Page<CommonRepairItemDto> getMyAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                JwtUtil.getIdFromRequest(request), RepairItemStateEnum.PROCESSING.getState(), null);
    }

    @Override
    public Page<CommonRepairItemDto> getMyAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                JwtUtil.getIdFromRequest(request), RepairItemStateEnum.PROCESSED.getState(), null);
    }

    @Override
    public Page<CommonRepairItemDto> getOtherAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                null, RepairItemStateEnum.PROCESSING.getState(), JwtUtil.getIdFromRequest(request));
    }

    @Override
    public Page<CommonRepairItemDto> getOtherAllProcessedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getRepairItemByState(PageUtil.getPage(pageRequest),
                null, RepairItemStateEnum.PROCESSED.getState(), JwtUtil.getIdFromRequest(request));
    }

    @Override
    public Page<CommonRepairItemDto> getUserAllMissedOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getUserRepairItem(PageUtil.getPage(pageRequest), JwtUtil.getIdFromRequest(request), RepairItemStateEnum.PENDING.getState());
    }

    @Override
    public Page<CommonRepairItemDto> getUserAllOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getUserRepairItem(PageUtil.getPage(pageRequest), JwtUtil.getIdFromRequest(request), null);
    }

    @Override
    public Page<CommonRepairItemDto> getUserAllOrdersInRepair(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getUserRepairItem(PageUtil.getPage(pageRequest), JwtUtil.getIdFromRequest(request), RepairItemStateEnum.PROCESSING.getState());
    }

    @Override
    public Page<CommonRepairItemDto> getUserAllHistoryOrders(PageRequest pageRequest, HttpServletRequest request) {
        return (Page<CommonRepairItemDto>) baseMapper.getUserRepairItem(PageUtil.getPage(pageRequest), JwtUtil.getIdFromRequest(request), null);
    }
    /**
     * 模糊搜索接口
     *
     * @param pageRequest
     * @param httpServletRequest
     * @param orderSearchEntity       搜索条件实体
     * @return
     */
    @Override
    public Page<CommonRepairItemDto> searchRepairItem(PageRequest pageRequest, HttpServletRequest httpServletRequest, OrderSearchEntity orderSearchEntity) {
        Integer idFromRequest = Integer.parseInt(JwtUtil.getIdFromRequest(httpServletRequest));
        Page<CommonRepairItemDto> commonRepairItemDtoIPage;
        //switch 添加筛选条件
        switch (orderSearchEntity.getRange()) {
            case 0:
                break;
            case 1://1我的处理中
                orderSearchEntity.setSearchState(3);
                orderSearchEntity.setUserId(idFromRequest);
                break;
            case 2://2我的已处理
                orderSearchEntity.setSearchState(4);
                orderSearchEntity.setUserId(idFromRequest);
                break;
            case 3://3他人处理中
                orderSearchEntity.setSearchState(3);
                break;
            case 4://4他人已处理
                orderSearchEntity.setSearchState(4);
                break;
            case 5://5所有待处理
                orderSearchEntity.setSearchState(1);
                break;
            default:
                throw new BadRequestException("搜索条件错误");
        }

        System.out.println(orderSearchEntity);
        commonRepairItemDtoIPage = (Page<CommonRepairItemDto>) baseMapper.searchRepairItem(PageUtil.getPage(pageRequest), orderSearchEntity);
        return commonRepairItemDtoIPage;
    }

    @Override
    public List<ExcelRepariItemDto> getList() {
        return baseMapper.getList();
    }

    @Override
    public List<StatisticsFromEquipment> getStatisList() {
        return baseMapper.getStatisList();
    }


}