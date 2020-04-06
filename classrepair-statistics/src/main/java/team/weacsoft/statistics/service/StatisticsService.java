package team.weacsoft.statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.IQaTypeService;
import team.weacsoft.repair.dto.response.StatisticsFromEquipment;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */

@Service
public class StatisticsService {
    
    private RepairItemStateServiceImpl repairItemService;
    private IQaTypeService qaTypeService;

    @Autowired
    public StatisticsService(RepairItemStateServiceImpl repairItemService, IQaTypeService qaTypeService) {
        this.repairItemService = repairItemService;
        this.qaTypeService = qaTypeService;
    }

    public Map<String, Integer> getStatisticsByEquipmentType(){
        List<RepairItem> repairItemList =  repairItemService.list();
        Map<Integer, Integer> data = new HashMap<>(20);
        for(RepairItem repairItem : repairItemList){
            Integer type = repairItem.getEquipmentType();
            int oldCount = data.getOrDefault(type, 0);
            data.put(type, oldCount+1);
        }
        return data.entrySet().stream()
                .collect(Collectors.toMap(
                        entry-> {
                                QaType qaType = qaTypeService.getById(entry.getKey());
                                if(qaType == null) {
                                    return "其他问题";
                                }else{
                                    return qaType.getTitle();
                                }
                            },  Map.Entry::getValue));
    }

    public Map<String, Integer> getStatisticsByClassroom(){
        List<RepairItem> repairItemList =  repairItemService.list();
        Map<String, Integer> data = new HashMap<>(20);
        for(RepairItem repairItem : repairItemList){
            String classroom = repairItem.getClassroom().split("-")[0];
            int oldCount = data.getOrDefault(classroom, 0);
            data.put(classroom, oldCount+1);
        }
        return data;
    }

    // todo 优化 返回名字  *优化成功
    public List<StatisticsFromEquipment> getStatisticsByReceiver(){
        List<StatisticsFromEquipment> repairItemList =repairItemService.getStatisList();
        List<StatisticsFromEquipment> statisticsFromEquipments = new ArrayList<>();
        Map<Integer, StatisticsFromEquipment> data = new HashMap<>();//容量

        for(StatisticsFromEquipment sdata : repairItemList) {
            if(!"".equals(sdata.getReceiver())){
                if(data.containsKey(sdata.getReceiver())){//如果该receiver已经存在,获取
                    StatisticsFromEquipment model = data.get(sdata.getReceiver());
                    model.add();
                }else{
                    /*StatisticsFromEquipment model=new StatisticsFromEquipment();
                    model.setReceiverIdentityId(sdata.getReceiverIdentityId());
                    model.setReceiver(sdata.getReceiver());
                    model.setReceiverName(sdata.getReceiverName());*/
                    data.put(sdata.getReceiver(),sdata);
                }
                /*int oldCount = data.getOrDefault(repairItem.getReceiver(), 0);
                data.put(repairItem.getReceiver(), oldCount+1);*/
            }
        }
        for(Integer i:data.keySet()){
            statisticsFromEquipments.add(data.get(i));
        }
        return statisticsFromEquipments;
    }
}
