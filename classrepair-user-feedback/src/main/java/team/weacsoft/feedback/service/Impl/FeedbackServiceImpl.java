package team.weacsoft.feedback.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.weacsoft.common.exception.BadRequestException;
import team.weacsoft.common.exception.EntityNotFoundException;
import team.weacsoft.common.persistence.PageRequest;
import team.weacsoft.common.utils.JwtUtil;
import team.weacsoft.common.utils.MyUtil;
import team.weacsoft.common.utils.PageUtil;
import team.weacsoft.feedback.entity.dto.request.FeedBackDto;
import team.weacsoft.feedback.entity.dto.response.FeedbackResp;
import team.weacsoft.feedback.entity.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.po.UserFeedback;
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
    @Override//用户侧-查看用户反馈
    public IPage<ManagerFeedbackResp> getFeedBack(PageRequest pageRequest, Integer state) {
        return baseMapper.getUserFeedback(PageUtil.getPage(pageRequest),state);
    }


    @Override//用户侧-修改反馈状态
    public FeedbackResp updateFeedBack(PageRequest pageRequest, HttpServletRequest request, Integer id, Integer status) {
        if(id==null||status==null){
            throw new EntityNotFoundException("参数","id","为空");
        }
        Integer update;
        if(status!=1&&status!=2&&status!=3){
            throw new BadRequestException(40088, "state状态错误");
        }
        update = baseMapper.update(id, status);
        if(update==0){
            throw new BadRequestException(40088, "输入id为无效id");
        }
        return new FeedbackResp(update,"修改成功");
    }
}
