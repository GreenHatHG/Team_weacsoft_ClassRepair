package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.dto.response.FeedbackResp;
import team.weacsoft.feedback.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FeedbackService {

    /**
     * 用户侧-获取意见反馈
     */
    List<ManagerFeedbackResp> getFeedBack(HttpServletRequest httpServletRequest, Integer state);


    /**
     * 用户侧-提交意见反馈
     * @return
     */
    FeedbackResp commitFeedBack(HttpServletRequest httpServletRequest, FeedBackDto feedBackDto);


    /**
     * 用户侧-修改反馈状态
     */
    IPage<UserFeedback> updateFeedBack(HttpServletRequest httpServletRequest, int id, String status);
}
