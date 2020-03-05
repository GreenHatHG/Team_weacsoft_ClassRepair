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
public class DeviceStatusticsService extends BaseStatisticsService{

    public DeviceStatusticsService() {
        this.type = "device";
    }

    @Override
    public JSONObject process(List<PeriodStatistics> list, String type) {
        Map<String, Integer> buildNum = new HashMap<>(13);
        for (PeriodStatistics dto : list) {
            if(StringUtils.equals(dto.getTitle(), type)){
                buildProcess(buildNum, dto);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("build", buildNum);
        return jsonObject;
    }
}
