package team.weacsoft.feedback.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import team.weacsoft.common.consts.FeedbackEnum;
import team.weacsoft.common.consts.RepairItemStateEnum;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.wx.TemplateMessage;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.po.UserFeedback;
import team.weacsoft.feedback.mapper.UserFeedbackMapper;
import team.weacsoft.feedback.service.BaseUserFeedbackService;
import team.weacsoft.repair.quartz.PushService;
import team.weacsoft.user.entity.UserInfo;
import team.weacsoft.user.service.IUserInfoService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName AddUserFeedBackImpl
 * @Author 魔法はまだ解けない
 * @date 2020.07.18 18:54
 */
@Service
public class AddUserFeedBackImpl extends BaseUserFeedbackService {

    private PushService pushService;
    private SimpMessagingTemplate wsTemplate;
    private UserFeedbackMapper userFeedbackMapper;
    protected IUserInfoService userInfoService;

    @Autowired
    public AddUserFeedBackImpl(PushService pushService,
                               IUserInfoService userInfoService,
                               SimpMessagingTemplate wsTemplate,
                               UserFeedbackMapper userFeedbackMapper){
        this.pushService=pushService;
        this.userInfoService=userInfoService;
        this.wsTemplate=wsTemplate;
        this.userFeedbackMapper=userFeedbackMapper;
    }

    //用户侧-提交意见反馈
    public FeedbackResp commitFeedBack(PageRequest pageRequest, HttpServletRequest request, FeedBackDto feedBackDto) {
        UserInfo userInfo = userInfoService.getById(JwtUtil.getIdFromRequest(request));
        UserFeedback userFeedBack = new UserFeedback();
        if(feedBackDto.getOrderPhone()!=null){
            userFeedBack.setOrderPhone(feedBackDto.getOrderPhone().toString());
        }else {
            userFeedBack.setOrderPhone("0");
        }
        userFeedBack.setFeedbackId(MyUtil.getId());//自动生成id
        userFeedBack.setOrderer(Integer.parseInt(JwtUtil.getIdFromRequest(request)));//下单人ID
        userFeedBack.setQuestion(feedBackDto.getContent());//设置问题
        this.save(userFeedBack);
        //这里的反馈内容需要判断一下长度
        String message=userFeedBack.getQuestion().length()>20 ? StrUtil.sub(userFeedBack.getQuestion(),0,17)+"...":userFeedBack.getQuestion();
        /**
         * 公众号推送消息
         */
//        pushService.sendUrgentMessage(repairItem);
        return new FeedbackResp(userFeedBack.getId(),"提交成功");
    }

    /**
     * 管理员提交回复信息
     * @param request
     * @param id    单号
     * @param content 描述
     * @return
     */
    public FeedbackResp answerFeedback(HttpServletRequest request,String id,String content){
        UserFeedback userFeedback = userFeedbackMapper.getUserFeedbackByNumber(id);
        if(userFeedback.getState()==2){
            throw new BadRequestException("问题已反馈");
        }
        UserInfo userInfo = userInfoService.getById(JwtUtil.getIdFromRequest(request));//获取接单用户
        UserInfo userInfo2 = userInfoService.getById(userFeedback.getOrderer());//获取接单用户
        //将反馈模板更新到数据库
        userFeedbackMapper.updateUserFeedback(id,
                Integer.parseInt(JwtUtil.getIdFromRequest(request)),
                content,2);
        //获取反馈信息
        //发送消息给用户
        //这里的反馈内容需要限制长度
        System.out.println(userInfo2.getOpenid());
        sendMessage(userFeedback,
                "o0Dsd5IoeVW7FS1kwcgSpm6AwD7M",
                StringUtils.isBlank(userInfo.getPhone())?"020-36903503\n020-36903084":userInfo.getPhone(),
                "已处理");
        return new FeedbackResp(userFeedback.getId(),"提交成功");
    }

}
