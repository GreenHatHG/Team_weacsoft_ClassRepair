package team.weacsoft.classrepair.statistics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.classrepair.commons.dto.Result;
import team.weacsoft.classrepair.commons.dto.ResultFactory;

/**
 * @author GreenHatHG
 * @menu 统计
 */

@RestController
@RequestMapping(value="/statistics")
@Validated
@Slf4j
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    /**
     * 统计所有设备报修次数
     * @return
     */
    @GetMapping("/type")
    public Result getStatisticsByEquipmentType(){
        return ResultFactory.buildSuccessResult(
                statisticsService.getStatisticsByEquipmentType());
    }

    /**
     * 统计所有教学楼报修次数
     * @return
     */
    @GetMapping("/classroom")
    public Result getStatisticsByClassroom(){
        return ResultFactory.buildSuccessResult(
                statisticsService.getStatisticsByClassroom());
    }

    /**
     * 统计一天三个时间段报修次数
     * @return
     */
    @GetMapping("/period")
    public Result getStatisticsByPeriod(){
        return ResultFactory.buildSuccessResult(
                statisticsService.getStatisticsByperiod());
    }

    /**
     * 统计所有维护人员接单次数
     * @return
     */
    @GetMapping("/receiver")
    public Result getStatisticsByReceiver(){
        return ResultFactory.buildSuccessResult(
                statisticsService.getStatisticsByReceiver());
    }
}
