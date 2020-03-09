package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.UserFeedBack;

import javax.servlet.http.HttpServletRequest;

public interface FeedBackService {

    /**
     * 用户侧-获取意见反馈
     */
    Page<UserFeedBack> getFeedBack(HttpServletRequest httpServletRequest);


    /**
     * 用户侧-提交意见反馈
     */
    Page<UserFeedBack> commitFeedBack(HttpServletRequest httpServletRequest, FeedBackDto feedBackDto);


    /**
     * 用户侧-修改反馈状态
     */
    Page<UserFeedBack> updateFeedBack(HttpServletRequest httpServletRequest, int id, String status);
}
