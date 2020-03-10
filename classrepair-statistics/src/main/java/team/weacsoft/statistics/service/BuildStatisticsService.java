package team.weacsoft.statistics.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.entity.PeriodStatistics;

import java.util.List;

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
        initDeviceNum();
        initDateNum();
        for (PeriodStatistics dto : list) {
            if(StringUtils.equals(dto.getClassroom().substring(0,2), type)){
                deviceProcess(dto);
                dateProcess(dto);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device", deviceNum);
        jsonObject.put("date", dateNum);
        return jsonObject;
    }
}
