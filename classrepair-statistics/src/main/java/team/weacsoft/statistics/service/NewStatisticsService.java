package team.weacsoft.statistics.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.entity.PeriodStatistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GreenHatHG
 * @since 2020-03-05
 */

@Component
public class NewStatisticsService extends BaseStatisticsService {

    public NewStatisticsService() {
        this.type = "new";
    }

    @Override
    public JSONObject process(List<PeriodStatistics> list, String type) {
        Map<String, Integer> deviceNum = new HashMap<>(10);
        Map<String, Integer> buildNum = new HashMap<>(13);
        Map<String, Integer> timeNum = new HashMap<>(5);
        Map<String, Integer> dateNum = new HashMap<>(50);
        for (PeriodStatistics dto : list) {
            buildProcess(buildNum, dto);
            deviceProcess(deviceNum, dto);
            timeProcess(timeNum, dto);
            dateProcess(dateNum, dto);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("build", buildNum);
        jsonObject.put("device", deviceNum);
        jsonObject.put("time", timeNum);
        jsonObject.put("date", dateNum);
        return jsonObject;
    }
}
