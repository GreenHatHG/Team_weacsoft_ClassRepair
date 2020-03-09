package team.weacsoft.feedback.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.weacsoft.feedback.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.UserFeedBack;
import team.weacsoft.feedback.mapper.UserFeedBackMapper;
import team.weacsoft.feedback.service.FeedBackService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName FeedBackServiceImpl
 * @Author 魔法はまだ解けない
 * @date 2020.03.08 17:45
 */
@Service
public class FeedBackServiceImpl extends ServiceImpl<UserFeedBackMapper, UserFeedBack> implements FeedBackService {
    @Override//用户侧-获取意见反馈
    public Page<UserFeedBack> getFeedBack(HttpServletRequest httpServletRequest) {

        return null;
    }

    @Override//用户侧-提交意见反馈
    public Page<UserFeedBack> commitFeedBack(HttpServletRequest httpServletRequest, FeedBackDto feedBackDto) {
        UserFeedBack userFeedBack = new UserFeedBack();
        userFeedBack.setOrderPhone(feedBackDto.getOrderPhone().toString());
        userFeedBack.setQuestion(feedBackDto.getContent());
        baseMapper.insert(userFeedBack);
        return null;
    }

    @Override//用户侧-修改反馈状态
    public Page<UserFeedBack> updateFeedBack(HttpServletRequest httpServletRequest, int id, String status) {

        return null;
    }
}
