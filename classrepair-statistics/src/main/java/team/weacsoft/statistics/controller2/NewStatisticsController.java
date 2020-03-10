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
import team.weacsoft.statistics.service.BaseStatisticsService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GreenHatHG
 * @since 2020-03-05
 */

@RestController
@RequestMapping(value="/api/v2/statistics")
@Validated
@Slf4j
public class NewStatisticsController {

    private ConcurrentHashMap<String , BaseStatisticsService> map = new ConcurrentHashMap<>();

    @Autowired
    public NewStatisticsController(List<BaseStatisticsService> list) {
        for (BaseStatisticsService base : list) {
            map.put(base.getType(), base);
        }
    }

    /**
     * 整体统计
     * 如果start_time、end_time不传，则返回的数据是当时调用接口的前七天内的数据，包括查询数据那一天
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/time")
    public ResponseEntity<ApiResp> getStatistics(@RequestParam(value = "start_time", required = false) Long startTime,
                                                         @RequestParam(value = "end_time", required = false) Long endTime){
        return ApiResp.ok(map.get("new").getStatistics(startTime, endTime, null));
    }

    /**
     * 查询某教学楼时间段内各设备报修数量
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/build")
    public ResponseEntity<ApiResp> getStatisticsByBuild(@RequestParam(value = "start_time", required = false) Long startTime,
                                                        @RequestParam(value = "end_time", required = false) Long endTime,
                                                        @RequestParam @NotBlank @Size(max = 100) String build ){
        return ApiResp.ok(map.get("build").getStatistics(startTime, endTime, build));
    }

    /**
     * 查询某教学楼时间段内各设备报修数量
     */
    @PreAuthorize("hasAnyRole('5', '6', '7', '9')")
    @GetMapping("/device")
    public ResponseEntity<ApiResp> getStatisticsByDevice(@RequestParam(value = "start_time", required = false) Long startTime,
                                                        @RequestParam(value = "end_time", required = false) Long endTime,
                                                        @RequestParam @NotBlank @Size(max = 100) String device ){
        return ApiResp.ok(map.get("device").getStatistics(startTime, endTime, device));
    }
}
