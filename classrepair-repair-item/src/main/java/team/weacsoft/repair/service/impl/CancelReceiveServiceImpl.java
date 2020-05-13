package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;
import team.weacsoft.user.entity.UserInfo;

/**
 * 维护人员取消接单
 * @author GreenHatHG
 * @since 2020-01-28
 */
@Service
public class CancelReceiveServiceImpl extends BaseUpdateRepairItemService {

    protected CancelReceiveServiceImpl() {
        this.type = "CancelReceive";
    }

    @Override
    protected RepairItem process(RepairItem repairItem, UserInfo userInfo) {
        if(!repairItem.getState().equals(RepairItemStateEnum.PROCESSING.getState())
             || !repairItem.getState().equals(RepairItemStateEnum.PENDING.getState())){
            throw new BadRequestException(40088, "该订单未处于处理中或者待处理状态，目前状态:"+
                    RepairItemStateEnum.getDescription(repairItem.getState()));
        }
        repairItem.setState(RepairItemStateEnum.PENDING.getState());
        repairItem.setReceiver(0);
        return repairItem;
    }

}
