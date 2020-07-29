package team.weacsoft.qa.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import team.weacsoft.qa.entity.QaAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 故障详情表 Mapper 接口
 * </p>
 *
 * @author GreenHatHG
 * @since 2020-01-27
 */
public interface QaAnswerMapper extends BaseMapper<QaAnswer> {
    List<QaAnswer> searchAnswers(String search);
}
