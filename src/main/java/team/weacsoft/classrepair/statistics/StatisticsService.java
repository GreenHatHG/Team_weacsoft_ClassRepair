package team.weacsoft.classrepair.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classrepair.bean.RepairItem;
import team.weacsoft.classrepair.service.RepairItemService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 */

//todo 缓存优化
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

    public Map<String, Integer> getStatisticsByClassroom(){
        List<RepairItem> repairItemList =  repairItemService.findAll();
        Map<String, Integer> data = new HashMap<>(20);
        for(RepairItem repairItem : repairItemList){
            String classroom = repairItem.getClassroom().split("-")[0];
            int oldCount = data.getOrDefault(classroom, 0);
            data.put(classroom, oldCount+1);
        }
        return data;
    }

    public Map<String, Integer> getStatisticsByperiod(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<RepairItem> repairItemList =  repairItemService.findAll();
        Map<String, Integer> data = new HashMap<>(3);
        //初始化
        data.put("早上", 0);
        data.put("下午", 0);
        data.put("晚上", 0);

        for(RepairItem repairItem : repairItemList) {
            String period = sdf.format(new Date((repairItem.getCreateTime())));
            if("05:00".compareTo(period) <= 0 && "12:00".compareTo(period) >= 0){
                int oldCount = data.getOrDefault("早上", 0);
                data.put("早上", oldCount+1);
            }
            else if("12:00".compareTo(period) < 0 && "19:00".compareTo(period) > 0){
                int oldCount = data.getOrDefault("下午", 0);
                data.put("下午", oldCount+1);
            }
            else if("19:00".compareTo(period) <= 0 && "23:00".compareTo(period) >= 0){
                int oldCount = data.getOrDefault("下午", 0);
                data.put("晚上", oldCount+1);
            }
        }
        return data;
    }

}
