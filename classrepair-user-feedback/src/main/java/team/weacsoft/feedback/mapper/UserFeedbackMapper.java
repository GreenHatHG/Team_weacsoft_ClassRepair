package team.weacsoft.feedback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import team.weacsoft.feedback.entity.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.po.UserFeedback;

public interface UserFeedbackMapper extends BaseMapper<UserFeedback> {
    IPage<ManagerFeedbackResp> getUserFeedback(IPage<T> page, @Param("state")Integer state);
    Integer update(Integer id,Integer state);
}
