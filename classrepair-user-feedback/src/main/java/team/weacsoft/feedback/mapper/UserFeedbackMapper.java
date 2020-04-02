package team.weacsoft.feedback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.RequestParam;
import team.weacsoft.feedback.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;

import java.util.List;

public interface UserFeedbackMapper extends BaseMapper<UserFeedback> {
    IPage<ManagerFeedbackResp> getUserFeedback(IPage<T> page, @Param("state")Integer state);
    Integer update(Integer id,Integer state);
}
