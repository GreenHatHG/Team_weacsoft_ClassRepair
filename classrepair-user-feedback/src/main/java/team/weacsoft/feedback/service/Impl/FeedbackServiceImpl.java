package team.weacsoft.feedback.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;
import team.weacsoft.feedback.mapper.UserFeedbackMapper;
import team.weacsoft.feedback.service.FeedbackService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName FeedBackServiceImpl
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 17:45
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements FeedbackService {
    @Override//用户侧-获取意见反馈
    public Page<UserFeedback> getFeedBack(HttpServletRequest httpServletRequest) {

        return null;
    }

    @Override//用户侧-提交意见反馈
    public FeedbackResp commitFeedBack(HttpServletRequest httpServletRequest, FeedBackDto feedBackDto) {
        UserFeedback userFeedBack = new UserFeedback();
        if(feedBackDto.getOrderPhone()!=null){
            userFeedBack.setOrderPhone(feedBackDto.getOrderPhone().toString());
        }else{
            userFeedBack.setOrderPhone("0");
        }
        userFeedBack.setQuestion(feedBackDto.getContent());
        baseMapper.insert(userFeedBack);
        FeedbackResp feedbackResp=new FeedbackResp(userFeedBack.getId());
        return feedbackResp;
    }

    @Override//用户侧-修改反馈状态
    public Page<UserFeedback> updateFeedBack(HttpServletRequest httpServletRequest, int id, String status) {

        return null;
    }
}
