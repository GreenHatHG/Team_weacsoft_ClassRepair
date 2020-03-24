package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;
import team.weacsoft.user.entity.UserInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 取消报修
 * @author GreenHatHG
 * @since 2020-01-28
 */
@Service
public class CancelRepairServiceImpl extends BaseUpdateRepairItemService {

    protected CancelRepairServiceImpl() {
        this.type = "CancelRepair";
    }

    @Override
    protected RepairItem process(RepairItem repairItem, UserInfo userInfo) {
        if(repairItem.getState()==null){
            throw new EntityNotFoundException("RepairIteam的","state","为空");
        }
        if(userInfo.getRole().equals(1) && !userInfo.getId().equals(repairItem.getOrderer())){
            throw new BadRequestException(40066, "普通人员只能取消自己下的单");
        }
        if(!(repairItem.getState().equals( RepairItemStateEnum.PENDING.getState()) || repairItem.getState().equals( RepairItemStateEnum.PROCESSING.getState())
            || repairItem.getState().equals( RepairItemStateEnum.CHECKED.getState()))){
            throw new BadRequestException(40077, "取消报修失败，该订单未处于未处理或者处理中状态，订单状态：" + RepairItemStateEnum.getStateById(repairItem.getState()));
        }
        repairItem.setState(RepairItemStateEnum.CANCELLED.getState());
        repairItem.setDeleteTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        return repairItem;
    }

}
