package team.weacsoft.statistics.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
public class BuildStatisticsService extends BaseStatisticsService {

    public BuildStatisticsService() {
        this.type = "build";
    }

    @Override
    public JSONObject process(List<PeriodStatistics> list, String type) {
        Map<String, Integer> deviceNum = new HashMap<>(10);
        Map<String, Integer> dateNum = new HashMap<>(50);
        for (PeriodStatistics dto : list) {
            if(StringUtils.equals(dto.getClassroom().substring(0,2), type)){
                deviceProcess(deviceNum, dto);
                dateProcess(dateNum, dto);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device", deviceNum);
        jsonObject.put("date", dateNum);
        return jsonObject;
    }
}
