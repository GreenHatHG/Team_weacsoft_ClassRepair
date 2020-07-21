package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.po.UserFeedback;

import javax.servlet.http.HttpServletRequest;

public interface FeedbackService extends IService<UserFeedback> {

    /**
     * 用户侧-获取意见反馈
     */
    IPage<ManagerFeedbackResp> getFeedBack(PageRequest pageRequest, Integer state);

    /**
     * 用户侧-修改反馈状态
     */
    FeedbackResp updateFeedBack(PageRequest pageRequest, HttpServletRequest request, Integer id, Integer status);

}
