package team.weacsoft.statistics.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import team.weacsoft.repair.entity.PeriodStatistics;

import java.util.List;

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
        initDeviceNum();
        initBuildNum();
        initTimeNum();
        initDateNum();
        for (PeriodStatistics dto : list) {
            buildProcess(dto);
            deviceProcess(dto);
            timeProcess(timeNum, dto);
            dateProcess(dto);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("build", buildNum);
        jsonObject.put("device", deviceNum);
        jsonObject.put("time", timeNum);
        jsonObject.put("date", dateNum);
        return jsonObject;
    }
}
