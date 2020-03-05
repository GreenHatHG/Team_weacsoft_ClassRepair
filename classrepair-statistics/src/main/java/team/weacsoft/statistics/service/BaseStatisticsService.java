package team.weacsoft.statistics.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.entity.PeriodStatistics;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 * @since 2020-03-05
 */

@Component
public abstract class BaseStatisticsService {

    @Autowired
    private RepairItemStateServiceImpl repairItemService;

    /**
     * 识别不同的实现类
     */
    protected String type;

    public String getType() {
        return type;
    }

    public JSONObject getStatistics(Long startTime, Long endTime, String type){
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
        return process(list, type);
    }

    public void deviceProcess(Map<String, Integer> deviceNum, PeriodStatistics dto){
        deviceNum.put(dto.getTitle(), deviceNum.getOrDefault(dto.getTitle(), 0) + 1);
    }

    public void dateProcess(Map<String, Integer> dateNum, PeriodStatistics dto){
        String now = DateUtil.format(new Date(dto.getCreateTime()*1000), "MM-dd");
        dateNum.put(now, dateNum.getOrDefault(now, 0) + 1);
    }

    public void buildProcess(Map<String, Integer> buildNum, PeriodStatistics dto){
        String build = dto.getClassroom().substring(0,2);
        buildNum.put(build, buildNum.getOrDefault(build, 0) + 1);
    }

    public void timeProcess(Map<String, Integer> timeNum, PeriodStatistics dto){
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
    }

    public abstract JSONObject process(List<PeriodStatistics> list, String type);
}
