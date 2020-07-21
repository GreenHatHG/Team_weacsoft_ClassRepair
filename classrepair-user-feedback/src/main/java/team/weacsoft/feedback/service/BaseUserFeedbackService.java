package team.weacsoft.feedback.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.wx.TemplateMessage;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.po.UserFeedback;
import team.weacsoft.feedback.mapper.UserFeedbackMapper;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 将微信消息推送抽象出来，交给
 * @ClassName BaseFeedbackMapper
 * @Author 魔法はまだ解けない
 * @date 2020.07.18 18:03
 */
public abstract class BaseUserFeedbackService extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements IService<UserFeedback> {

    @Autowired
    protected TemplateMessage templateMessage;

    /**
     * 调用模板方法发送消息
     *
     * @param userFeedback 反馈订单
     * @param openId       目标用户id
     * @param phone        反馈状态
     * @param type         备注
     */
    @Async
    protected void sendMessage(UserFeedback userFeedback, String openId, String phone, String type) {
        if (StringUtils.isNotBlank(openId)) {//判断用户是否存在
            templateMessage.buildFeedbackMapAndSend(
                    openId,
                    userFeedback.getQuestion(),
                    userFeedback.getCreateTime(),
                    userFeedback.getAnswer(),
                    phone, type);
        }
    }

}
