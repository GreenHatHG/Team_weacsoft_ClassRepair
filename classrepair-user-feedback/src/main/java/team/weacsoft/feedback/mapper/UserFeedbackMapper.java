package team.weacsoft.feedback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import team.weacsoft.feedback.dto.response.ManagerFeedbackResp;
import team.weacsoft.feedback.entity.UserFeedback;

import java.util.List;

public interface UserFeedbackMapper extends BaseMapper<UserFeedback> {
    List<ManagerFeedbackResp> getUserFeedback(@Param("state")Integer state);
    void update(Integer id,Integer state);
}
