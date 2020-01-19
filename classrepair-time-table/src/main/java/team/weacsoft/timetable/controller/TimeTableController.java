package team.weacsoft.timetable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.timetable.service.TimeTableService;

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
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    /**
     * 签到/签退
     */
    @PreAuthorize("hasAnyRole('2', '3', '4', '5')")
    @PostMapping("/on_duty")
    public ResponseEntity<ApiResp> SignInOrOut(@RequestParam @NotNull  @Min(1) @Max(2) Integer state, HttpServletRequest request){
        return ApiResp.ok(timeTableService.signInOrOut(state, request));
    }
}
