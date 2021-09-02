package team.weacsoft.feedback.controller2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.service.FeedbackService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName FeedBackController
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 15:08
 */
@Api(value = "AddFeedbackController",  tags = "反馈模块 | 反馈管理 AddFeedbackController ")
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
     * 管理员-查看所有意见反馈
     */
    @ApiOperation(value="管理员-查看所有意见反馈", notes="")
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")//权限
    @GetMapping("/getFeedBack")
    public ResponseEntity<ApiResp> getFeedBack(PageRequest pageRequest,
                                               @RequestParam(value = "status", required = false) Integer state){
        return ApiResp.ok(feedBackService.getFeedBack(pageRequest,state));
    }

    /**
     * 管理员-修改反馈状态
     * 反馈状态：
     */
    @ApiOperation(value="管理员-修改反馈状态", notes="")
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")
    @PutMapping("/updateFeedBack")
    public ResponseEntity<ApiResp> updateFeedBack(PageRequest pageRequest, HttpServletRequest request,
                                                  @RequestParam(value = "id") Integer id,
                                                  @RequestParam(value = "status") Integer status){
        return ApiResp.ok(feedBackService.updateFeedBack(pageRequest,request,id,status));
    }
}
