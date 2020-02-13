package team.weacsoft.timetable.controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.timetable.service.ITimeTableService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 值班
 * @author GreenHatHG
 */
@Validated
@RestController
@RequestMapping(value="/api/v1")
public class TimeTableController1 {

    @Autowired
    private ITimeTableService timeTableService;

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
    public ResponseEntity<ApiResp> findAllOnline(PageRequest pageRequest){
        return ApiResp.ok(timeTableService.getAllOnline());
    }

    /**
     * 获取我的当前值班状态
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @GetMapping("/user/duty_state")
    public ResponseEntity<ApiResp> getMyState(HttpServletRequest request){
        return ApiResp.ok(timeTableService.getMyState(request));
    }
}
