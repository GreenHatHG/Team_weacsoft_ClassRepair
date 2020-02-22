package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
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
        if(repairItem.getState() != 2){
            throw new BadRequestException(40088, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(3);
        return repairItem;
    }

}
