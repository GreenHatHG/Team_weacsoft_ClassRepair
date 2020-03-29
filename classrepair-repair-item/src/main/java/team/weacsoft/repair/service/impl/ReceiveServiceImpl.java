package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;
import team.weacsoft.user.entity.UserInfo;

/**
 * 维修人员接单
 * @author GreenHatHG
 * @since 2020-01-28
 */
@Service
public class ReceiveServiceImpl extends BaseUpdateRepairItemService {

    protected ReceiveServiceImpl() {
        this.type = "Receive";
    }

    @Override
    protected RepairItem process(RepairItem repairItem, UserInfo userInfo) {
        if(repairItem.getState()==null){
            throw new EntityNotFoundException("RepairIteam的","state","为空");
        }
        if(!repairItem.getState().equals(RepairItemStateEnum.PENDING.getState())){
            throw new BadRequestException(40050, "接单失败，该订单未处于接单状态，订单状态：" + RepairItemStateEnum.getDescription(repairItem.getState()));
        }
        repairItem.setState(RepairItemStateEnum.PROCESSING.getState());
        repairItem.setReceiver(userInfo.getId());
        return repairItem;
    }

}
