package team.weacsoft.feedback.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.dto.response.FeedbackResp;
import team.weacsoft.feedback.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;
import team.weacsoft.feedback.mapper.UserFeedbackMapper;
import team.weacsoft.feedback.service.FeedbackService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @ClassName FeedBackServiceImpl
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 17:45
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements FeedbackService {
    @Override//用户侧-查看用户反馈
    public List<ManagerFeedbackResp> getFeedBack(HttpServletRequest httpServletRequest, Integer state) {
        return baseMapper.getUserFeedback(state);
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
    public IPage<UserFeedback> updateFeedBack(HttpServletRequest httpServletRequest, Integer id, Integer status) {
        baseMapper.update(id,status);
        return null;
    }
}
