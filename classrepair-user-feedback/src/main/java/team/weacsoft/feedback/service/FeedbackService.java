package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;

import javax.servlet.http.HttpServletRequest;

public interface FeedbackService {

    /**
     * 用户侧-获取意见反馈
     */
    Page<UserFeedback> getFeedBack(HttpServletRequest httpServletRequest);


    /**
     * 用户侧-提交意见反馈
     * @return
     */
    FeedbackResp commitFeedBack(HttpServletRequest httpServletRequest, FeedBackDto feedBackDto);


    /**
     * 用户侧-修改反馈状态
     */
    Page<UserFeedback> updateFeedBack(HttpServletRequest httpServletRequest, int id, String status);
}
