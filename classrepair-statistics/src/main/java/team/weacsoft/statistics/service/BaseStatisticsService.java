package team.weacsoft.statistics.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.weacsoft.classroom.entity.Classroom;
import team.weacsoft.classroom.service.impl.ClassroomServiceImpl;
import team.weacsoft.qa.entity.QaType;
import team.weacsoft.qa.service.impl.QaTypeServiceImpl;
import team.weacsoft.repair.entity.PeriodStatistics;
import team.weacsoft.repair.service.impl.RepairItemStateServiceImpl;

import java.util.*;

/**
 * @author GreenHatHG
 * @since 2020-03-05
 */

@Component
public abstract class BaseStatisticsService {

    @Autowired
    private RepairItemStateServiceImpl repairItemService;

    @Autowired
    private QaTypeServiceImpl qaTypeService;

    @Autowired
    private ClassroomServiceImpl classroomService;

    /**
     * 识别不同的实现类
     */
    protected String type;

    public String getType() {
        return type;
    }

    protected Long startTime;
    protected Long endTime;

    protected Map<String, Integer> dateNum;
    protected Map<String, Integer> deviceNum;
    protected Map<String, Integer> buildNum;
    protected Map<String, Integer> timeNum;

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
        this.startTime = startTime;
        this.endTime = endTime;
        List<PeriodStatistics> list = repairItemService.getBaseMapper().typeStatisticsDao(startTime, endTime);
        return process(list, type);
    }

    public void initDateNum(){
        dateNum = new TreeMap<>();

        DateTime start = new DateTime(startTime*1000);
        DateTime end = new DateTime(endTime*1000);

        int betweenDay = (int)DateUtil.between(start, end, DateUnit.DAY);
        while(betweenDay >= 0){
            dateNum.put(DateUtil.format(DateUtil.offsetDay(start, betweenDay--), "MM-dd"), 0);
        }
    }

    public void initDeviceNum(){
        List<QaType> list = qaTypeService.list();
        deviceNum = new HashMap<>(list.size());
        list.forEach(qaType -> {
            deviceNum.put(qaType.getTitle(), 0);
        });
    }

    public void initBuildNum(){
        List<Classroom> list = classroomService.getBaseMapper().selectList(
                new QueryWrapper<Classroom>().select("distinct build"));
        buildNum = new HashMap<>(list.size());
        list.forEach(classroom ->{
            buildNum.put(classroom.getBuild(), 0);
        });
    }

    public void initTimeNum(){
        timeNum = new TreeMap<>();
        timeNum.put("5:00-10:15", 0);
        timeNum.put("10:15-12:00", 0);
        timeNum.put("14:00-15:50", 0);
        timeNum.put("15:50-17:20", 0);
        timeNum.put("18:00-23:00", 0);
    }

    public void deviceProcess(PeriodStatistics dto){
        deviceNum.put(dto.getTitle(), deviceNum.getOrDefault(dto.getTitle(), 0) + 1);
    }

    public void dateProcess(PeriodStatistics dto){
        String now = DateUtil.format(new Date(dto.getCreateTime()*1000), "MM-dd");
        dateNum.put(now, dateNum.getOrDefault(now, 0) + 1);
    }

    public void buildProcess(PeriodStatistics dto){
        String build = dto.getClassroom().substring(0,2);
        buildNum.put(build, buildNum.getOrDefault(build, 0) + 1);
    }

    public void timeProcess(Map<String, Integer> timeNum, PeriodStatistics dto){
        String period = DateUtil.format(new Date(dto.getCreateTime()*1000), "HH:mm");
        if("05:00".compareTo(period) <= 0 && "10:15".compareTo(period) >= 0){
            timeNum.put("5:00-10:15", timeNum.getOrDefault("8:00-10:15", 0)+1);
        }
        else if("10:15".compareTo(period) <= 0 && "12:00".compareTo(period) >= 0){
            timeNum.put("10:15-12:00", timeNum.getOrDefault("10:15-12:00", 0)+1);
        }
        else if("14:00".compareTo(period) <= 0 && "15:50".compareTo(period) >= 0){
            timeNum.put("14:00-15:50", timeNum.getOrDefault("14:00-15:50", 0)+1);
        }
        else if("15:50".compareTo(period) <= 0 && "17:20".compareTo(period) >= 0){
            timeNum.put("15:50-17:20", timeNum.getOrDefault("15:50-17:20", 0)+1);
        }
        else if("18:00".compareTo(period) <= 0 && "23:00".compareTo(period) >= 0){
            timeNum.put("18:00-23:00", timeNum.getOrDefault("18:00-23:00", 0)+1);
        }
    }

    public abstract JSONObject process(List<PeriodStatistics> list, String type);
}
