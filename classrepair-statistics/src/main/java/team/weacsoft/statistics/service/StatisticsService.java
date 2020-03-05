package team.weacsoft.statistics.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.IQaTypeService;
import team.weacsoft.repair.entity.PeriodStatistics;
import team.weacsoft.repair.entity.RepairItem;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;

import java.util.*;
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

    //todo 考虑将数据库时间戳换为毫秒级
    public JSONObject getStatisticsByperiod(Long startTime, Long endTime){
        if(startTime == null || endTime == null){
            endTime = DateUtil.current(Boolean.FALSE) / 1000;
            Calendar calendar = DateUtil.offsetDay(new Date(), -6).toCalendar();
            //设置零点时间
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startTime = calendar.getTimeInMillis() / 1000;
        }

        List<PeriodStatistics> list = repairItemService.getBaseMapper().typeStatisticsDao(startTime, endTime);
        Map<String, Integer> deviceNum = new HashMap<>(10);
        Map<String, Integer> buildNum = new HashMap<>(13);
        Map<String, Integer> timeNum = new HashMap<>(5);
        Map<String, Integer> dateNum = new HashMap<>(50);
        for (PeriodStatistics dto : list) {

            deviceNum.put(dto.getTitle(), deviceNum.getOrDefault(dto.getTitle(), 0) + 1);

            String build = dto.getClassroom().substring(0,2);
            buildNum.put(build, buildNum.getOrDefault(build, 0) + 1);

            String period = DateUtil.format(new Date(dto.getCreateTime()*1000), "HH:mm");
            if("08:00".compareTo(period) <= 0 && "10:15".compareTo(period) >= 0){
                timeNum.put("8:00-10︰15", timeNum.getOrDefault("8:00-10︰15", 0)+1);
            }
            else if("10︰15".compareTo(period) <= 0 && "12︰00".compareTo(period) >= 0){
                timeNum.put("10︰15-12︰00", timeNum.getOrDefault("10︰15-12︰00", 0)+1);
            }
            else if("14:00".compareTo(period) <= 0 && "15︰50".compareTo(period) >= 0){
                timeNum.put("14:00-15︰50", timeNum.getOrDefault("14:00-15︰50", 0)+1);
            }
            else if("15︰50".compareTo(period) <= 0 && "17︰20".compareTo(period) >= 0){
                timeNum.put("15︰50-17︰20", timeNum.getOrDefault("15︰50-17︰20", 0)+1);
            }
            else if("18:00".compareTo(period) <= 0 && "21:00".compareTo(period) >= 0){
                timeNum.put("18:00-21:00", timeNum.getOrDefault("18:00-21:00", 0)+1);
            }

            String now = DateUtil.format(new Date(dto.getCreateTime()*1000), "MM-dd");
            dateNum.put(now, dateNum.getOrDefault(now, 0) + 1);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("build", buildNum);
        jsonObject.put("device", deviceNum);
        jsonObject.put("time", timeNum);
        jsonObject.put("date", dateNum);
        return jsonObject;
    }

    // todo 优化 返回名字
    public Map<Integer, Integer> getStatisticsByReceiver(){
        List<RepairItem> repairItemList =  repairItemService.list();
        Map<Integer, Integer> data = new HashMap<>(3);

        for(RepairItem repairItem : repairItemList) {
            if(!"".equals(repairItem.getReceiver())){
                int oldCount = data.getOrDefault(repairItem.getReceiver(), 0);
                data.put(repairItem.getReceiver(), oldCount+1);
            }
        }
        return data;
    }
}
