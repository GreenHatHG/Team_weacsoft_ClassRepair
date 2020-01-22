package team.weacsoft.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.utils.JsonUtil;
import team.weacsoft.timetable.domain.TimeTableDo;
import team.weacsoft.timetable.service.TimeTableService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 值班
 * @author GreenHatHG
 */
@Validated
@RestController
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    /**
     * 签到/签退
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @PostMapping("/on_duty")
    public ResponseEntity<ApiResp> signInOrOut(@RequestParam @NotNull  @Min(1) @Max(2) Integer state, HttpServletRequest request){
        return ApiResp.ok(timeTableService.signInOrOut(state, request));
    }

    /**
     * 返回当前值班中人员及签到时间等信息
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/online")
    public ResponseEntity<ApiResp> findAllOnline(){
        List<TimeTableDo> list = timeTableService.findAllOnline();
        return list == null ? ApiResp.ok("没有人员在线")
                : ApiResp.ok(JsonUtil.arrayExclude(list, "deleteTime", "updateTime"));
    }
}
