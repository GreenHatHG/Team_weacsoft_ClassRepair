package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.dto.response.FeedbackResp;
import team.weacsoft.feedback.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;

import javax.servlet.http.HttpServletRequest;

public interface FeedbackService {

    /**
     * 用户侧-获取意见反馈
     */
    IPage<ManagerFeedbackResp> getFeedBack(PageRequest pageRequest, Integer state);


    /**
     * 用户侧-提交意见反馈
     * @return
     */
    FeedbackResp commitFeedBack(PageRequest pageRequest, HttpServletRequest request, FeedBackDto feedBackDto);


    /**
     * 用户侧-修改反馈状态
     */
    FeedbackResp updateFeedBack(PageRequest pageRequest, HttpServletRequest request, Integer id, Integer status);
}
