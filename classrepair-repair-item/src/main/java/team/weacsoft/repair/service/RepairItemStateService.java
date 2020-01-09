package team.weacsoft.repair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.domain.RepairItemDo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GreenHatHG
 */

@Component
public class RepairItemStateService {

    @Autowired
    private RepairItemService repairItemService;

    public List<RepairItemDo> getAllMissedOrder(){
        return getMissedOrdersFromAll(repairItemService.findAll());
    }

    public List<RepairItemDo> getMyAllMissedOrders(String userId){
        return getMissedOrdersFromAll(repairItemService.findAllByOrderUserId(userId));
    }

    public List<RepairItemDo> getMyAllProcessedOrders(String userId){
        return getProcessedOrdersFromAll(repairItemService.findAllByOrderUserId(userId));
    }

    // todo 重构
    /**
     * 从特定的结果集中找出所有未接订单
     * @param repairItemList
     * @return
     */
    private List<RepairItemDo> getMissedOrdersFromAll(List<RepairItemDo> repairItemList){
        List<RepairItemDo> resp = new ArrayList<>();
        for(RepairItemDo repairItem : repairItemList){
            if("".equals(repairItem.getReceiverUserId())
                    && repairItem.getDeleteTime() == 0 && repairItem.getState() == 1){
                resp.add((repairItem));
            }
        }
        return resp;
    }

    /**
     * 从特定的结果集中找出所有已经处理的订单
     * @param repairItemList
     * @return
     */
    private List<RepairItemDo> getProcessedOrdersFromAll(List<RepairItemDo> repairItemList){
        List<RepairItemDo> resp = new ArrayList<>();
        for(RepairItemDo repairItem : repairItemList){
            if(!"".equals(repairItem.getReceiverUserId())
                    && repairItem.getDeleteTime() == 0 && repairItem.getState() == 3){
                resp.add((repairItem));
            }
        }
        return resp;
    }
}
