package team.weacsoft.statistics.controller1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.statistics.service.StatisticsService;

/**
 * @author GreenHatHG
 * @since 2020-01-28
 */
@RestController
@RequestMapping(value="/api/v1/statistics")
@Validated
@Slf4j
public class StatisticsController1 {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * 统计所有设备报修次数
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @GetMapping("/type")
    public ResponseEntity<ApiResp> getStatisticsByEquipmentType(){
        return ApiResp.ok(
                statisticsService.getStatisticsByEquipmentType());
    }

    /**
     * 统计所有教学楼报修次数
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @GetMapping("/classroom")
    public ResponseEntity<ApiResp> getStatisticsByClassroom(){
        return ApiResp.ok(
                statisticsService.getStatisticsByClassroom());
    }

    /**
     * 统计一天三个时间段报修次数
     * 早上：5:00-12:00
     * 下午：12:00-19:00
     * 晚上: 19:00-23:00
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @GetMapping("/period")
    public ResponseEntity<ApiResp> getStatisticsByPeriod(){
        return ApiResp.ok(
                statisticsService.getStatisticsByperiod());
    }

    /**
     * 统计所有维护人员接单次数
     * @return
     */
    @PreAuthorize("hasAnyRole('3', '4', '5')")
    @GetMapping("/receiver")
    public ResponseEntity<ApiResp> getStatisticsByReceiver(){
        return ApiResp.ok(
                statisticsService.getStatisticsByReceiver());
    }
}
