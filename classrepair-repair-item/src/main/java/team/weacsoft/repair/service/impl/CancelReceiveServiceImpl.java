package team.weacsoft.repair.service.impl;

import org.springframework.stereotype.Service;
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
        if(repairItem.getState() != 2){
            throw new BadRequestException(40088, "该订单未处于处理中状态，目前状态:"+repairItem.getState());
        }
        repairItem.setState(1);
        repairItem.setReceiver(0);
        return repairItem;
    }

    @Override
    protected void sendMessage(RepairItem repairItem, UserInfo userInfo) {
        sendMessage(repairItem, userInfo.getOpenid(), "已取消", "取消者：" + userInfo.getName());
        sendMessage(repairItem, userInfoService.getById(repairItem.getOrderer()).getOpenid(), "已取消", "取消者：" + userInfo.getName());
    }

}
