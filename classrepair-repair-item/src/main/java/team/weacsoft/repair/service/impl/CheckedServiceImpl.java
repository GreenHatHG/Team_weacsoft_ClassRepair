package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.BaseUpdateRepairItemService;
import team.weacsoft.user.entity.UserInfo;

/**
 * 订单已查看
 * @author GreenHatHG
 * @since 2020-05-13
 */
@Service
public class CheckedServiceImpl extends BaseUpdateRepairItemService {

    protected CheckedServiceImpl() {
        this.type = "Check";
    }

    @Override
    protected RepairItem process(RepairItem repairItem, UserInfo userInfo) {
        if(!repairItem.getState().equals(RepairItemStateEnum.PENDING.getState())){
            throw new BadRequestException(40088, "该订单未处于待处理状态，目前状态:"+RepairItemStateEnum.getDescription(repairItem.getState()));
        }
        repairItem.setState(RepairItemStateEnum.CHECKED.getState());
        return repairItem;
    }

}
