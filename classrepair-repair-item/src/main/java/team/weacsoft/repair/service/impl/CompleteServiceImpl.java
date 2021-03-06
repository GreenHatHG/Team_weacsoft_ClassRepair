package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;
import team.weacsoft.user.entity.UserInfo;

/**
 * 完成报修
 * @author GreenHatHG
 * @since 2020-01-28
 */
@Service
public class CompleteServiceImpl extends BaseUpdateRepairItemService {

    protected CompleteServiceImpl() {
        this.type = "Complete";
    }

    @Override
    protected RepairItem process(RepairItem repairItem, UserInfo userInfo) {
        if(!repairItem.getState().equals(RepairItemStateEnum.PROCESSING.getState())
                && !repairItem.getState().equals( RepairItemStateEnum.CHECKED.getState())){
            throw new BadRequestException(40088, "该订单未处于处理中状态或查看完状态，目前状态:"+RepairItemStateEnum.getDescription(repairItem.getState()));
        }
        repairItem.setState(RepairItemStateEnum.PROCESSED.getState());
        return repairItem;
    }

}
