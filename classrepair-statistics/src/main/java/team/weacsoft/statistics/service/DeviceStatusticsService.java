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
public class DeviceStatusticsService extends BaseStatisticsService{

    public DeviceStatusticsService() {
        this.type = "device";
    }

    @Override
    public JSONObject process(List<PeriodStatistics> list, String type) {
        initBuildNum();
        for (PeriodStatistics dto : list) {
            if(StringUtils.equals(dto.getTitle(), type)){
                buildProcess(dto);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("build", buildNum);
        return jsonObject;
    }
}
