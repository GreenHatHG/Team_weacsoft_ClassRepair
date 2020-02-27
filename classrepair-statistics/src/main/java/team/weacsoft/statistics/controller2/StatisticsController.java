package team.weacsoft.statistics.controller2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.statistics.service.StatisticsService;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */
@RestController
@RequestMapping(value="/api/v2/statistics")
@Validated
@Slf4j
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 统计所有设备报修次数
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/type")
    public ResponseEntity<ApiResp> getStatisticsByEquipmentType(){
        return ApiResp.ok(
                statisticsService.getStatisticsByEquipmentType());
    }

    /**
     * 统计所有教学楼报修次数
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/classroom")
    public ResponseEntity<ApiResp> getStatisticsByClassroom(){
        return ApiResp.ok(
                statisticsService.getStatisticsByClassroom());
    }

    /**
     * 统计时间段内的报修数量
     * 如果start_time、end_time不传，则返回的数据是当时调用接口的前七天内的数据，包括查询数据那一天
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/time")
    public ResponseEntity<ApiResp> getStatisticsByPeriod(@RequestParam(value = "start_time", required = false) Long startTime,
                                                             @RequestParam(value = "end_time", required = false) Long endTime){
        return ApiResp.ok(statisticsService.getStatisticsByperiod(startTime, endTime));
    }

    /**
     * 统计所有维护人员接单次数
     * @return
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/receiver")
    public ResponseEntity<ApiResp> getStatisticsByReceiver(){
        return ApiResp.ok(
                statisticsService.getStatisticsByReceiver());
    }
}
