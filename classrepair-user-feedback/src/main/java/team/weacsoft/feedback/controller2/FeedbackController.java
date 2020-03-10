package team.weacsoft.feedback.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.log.Log;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.service.FeedbackService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Description
 * @ClassName FeedBackController
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 15:08
 */
@RestController
@RequestMapping("/api/v2/feedback")
@Validated
public class FeedbackController {
    FeedbackService feedBackService;

    @Autowired
    FeedbackController(FeedbackService feedBackService){
        this.feedBackService=feedBackService;
    }

    /**
     * 管理员-查看意见反馈
     */
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")//权限
    @GetMapping("/getFeedBack")
    public ResponseEntity<ApiResp> getFeedBack(HttpServletRequest httpServletRequest,
                                               @RequestParam(value = "status", required = false) Integer state){
        return ApiResp.ok(feedBackService.getFeedBack(httpServletRequest,state));
    }

    /**
     * 用户侧-提交意见反馈
     */
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")//权限
    @PostMapping("/commitFeedBack")//??
    public ResponseEntity<ApiResp> commitFeedBack(HttpServletRequest httpServletRequest,
                                                  @Validated @RequestBody FeedBackDto feedBackDto){
        return ApiResp.ok(feedBackService.commitFeedBack(httpServletRequest,feedBackDto));
    }

    /**
     * 管理员-修改反馈状态
     */
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")
    @PutMapping("/updateFeedBack")
    public ResponseEntity<ApiResp> updateFeedBack(HttpServletRequest httpServletRequest,
                                                  @RequestParam(value = "id") Integer id,
                                                  @RequestParam(value = "status") Integer status){
        return ApiResp.ok(feedBackService.updateFeedBack(httpServletRequest,id,status));
    }
}
