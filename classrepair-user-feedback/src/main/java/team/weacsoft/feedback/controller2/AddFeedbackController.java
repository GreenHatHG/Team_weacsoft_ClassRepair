package team.weacsoft.feedback.controller2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.weacsoft.common.exception.handler.ApiResp;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.dto.response.FeedbackResp;
import team.weacsoft.feedback.service.FeedbackService;
import team.weacsoft.feedback.service.Impl.AddUserFeedBackImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @Description
 * @ClassName AddFeedbackController
 * @Author 魔法はまだ解けない
 * @date 2020.07.18 16:06
 */
@Api(value = "AddFeedbackController",  tags = "反馈模块 | 添加反馈 AddFeedbackController ")
@RequestMapping("/api/v2/feedback")
@RestController
public class AddFeedbackController {
    AddUserFeedBackImpl addUserFeedBack;

    @Autowired
    public AddFeedbackController(AddUserFeedBackImpl addUserFeedBack){
        this.addUserFeedBack=addUserFeedBack;
    }

    /**
     * 用户侧-提交意见反馈
     */
    @ApiOperation(value="用户侧-提交意见反馈", notes="")
    @PreAuthorize("hasAnyRole('1', '4', '5', '6', '7', '9')")//权限
    @PostMapping("/commitFeedBack")//??
    public ResponseEntity<ApiResp> commitFeedBack(PageRequest pageRequest, HttpServletRequest request,
                                                  @Validated @RequestBody FeedBackDto feedBackDto){
        FeedbackResp feedbackResp = addUserFeedBack.commitFeedBack(pageRequest, request, feedBackDto);
        return ApiResp.ok(feedbackResp);
    }

    /**
     *  管理员-回复意见反馈
     */
    @ApiOperation(value="管理员-回复意见反馈", notes="")
    @PreAuthorize("hasAnyRole('4', '5', '6', '7', '9')")//权限
    @PostMapping("/answer")//
    public ResponseEntity<ApiResp> answerFeedback(HttpServletRequest request,
                                                  @NotNull String id,
                                                  @Validated @RequestBody FeedBackDto content){
        FeedbackResp feedbackResp=addUserFeedBack.answerFeedback(request,id,content.getContent());
        return ApiResp.ok(feedbackResp);
    }



}
