package team.weacsoft.classrepair.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.service.RepairItemService;

import java.util.*;

/**
 * @author GreenHatHG
 */

@Component
public class StatisticsService {

    @Autowired
    private RepairItemService repairItemService;

    public Map<String, Integer> getStatisticsByEquipmentType(){
        List<RepairItem> repairItemList =  repairItemService.findAll();
        Map<String, Integer> data = new HashMap<>(20);
        for(RepairItem repairItem : repairItemList){
            String type = repairItem.getEquipmentType();
            int oldCount = data.getOrDefault(type, 0);
            data.put(type, oldCount+1);
        }
        return data;
    }

}
